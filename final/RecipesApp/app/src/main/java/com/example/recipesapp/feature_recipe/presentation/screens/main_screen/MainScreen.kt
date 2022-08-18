package com.example.recipesapp.feature_recipe.presentation.screens.main_screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.recipesapp.feature_recipe.domain.model.drawer.MenuItem
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.bottom_bar.BottomBar
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.top_app_bar.components.TopBar
import com.example.recipesapp.feature_recipe.presentation.navigation.RecipesNavigation
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.drawer.DrawerBody
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.drawer.DrawerHeader
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    viewModelMain: ViewModelMain = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
    scaffoldState = scaffoldState,
        topBar = {
            TopBar(navController, scaffoldState)
        },
        bottomBar = {
            BottomBar(navController)
        },
        floatingActionButton = {
            if(currentDestination?.hierarchy?.any{
                    it.route == RouteScreen.MEAL.name+"/{id}/{name}" } == true){

                val id = navBackStackEntry?.arguments?.getString("id")

                FloatingActionButton(onClick = {
                                         navController.popBackStack()
                                        viewModelMain.insertRecipe(Recipe(id!!)
                                     ) }, backgroundColor = Colors.lightBrownPastel.copy(.8f)) {
                    Icon(imageVector = Icons.Default.Star,
                        contentDescription = "Star Icon",
                        tint = Colors.blackPastel)
                }

            }else if(currentDestination?.hierarchy?.any{
                    it.route == RouteScreen.FAVORITES_ITEM.name+"/{id}/{name}" } == true){

                val id = navBackStackEntry?.arguments?.getString("id")

                val scope = rememberCoroutineScope()
                FloatingActionButton(onClick = {
                                    viewModelMain.deleteNote(id!!)
                                    navController.navigate(RouteScreen.FAVORITES.name)
                     }, backgroundColor = Colors.lightBrownPastel.copy(.8f)) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Icon",
                        tint = Colors.blackPastel)
                }
            }
        },

        drawerContent = {
            if(currentDestination?.hierarchy?.any{
                    it.route == RouteScreen.HOME.name ||
                    it.route == RouteScreen.FAVORITES.name ||
                    it.route == RouteScreen.SETTING.name } == true){

                DrawerHeader()

              DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "h1",
                            title = "Meals",
                            contentDescription = "Go to home screen",
                            icon = Icons.Default.Fastfood,
                            route = RouteScreen.HOME.name
                        ),
                        MenuItem(
                            id = "settings",
                            title = "Settings",
                            contentDescription = "Go to settings screen",
                            icon = Icons.Default.Settings,
                            route = RouteScreen.SETTING.name
                        ),
                    ),
                    onItemClick = {
                        navController.navigate(it.route)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                )
            }
        },
        backgroundColor = Colors.lightBrownPastel.copy(.2f)


    ) {
        RecipesNavigation(navController)
    }
}
