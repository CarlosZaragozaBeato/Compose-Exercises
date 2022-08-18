package com.example.recipesapp.feature_recipe.presentation.screens.main_screen.top_app_bar.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import kotlinx.coroutines.launch

@Composable
fun TopBar(navController: NavHostController, scaffoldState: ScaffoldState){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scope = rememberCoroutineScope()




    TopAppBar(
        backgroundColor = Colors.redPastel,
        contentPadding = PaddingValues(vertical = 7.5.dp)
    ){


        if(currentDestination?.hierarchy?.any{
                it.route == RouteScreen.HOME.name } == true){

            MainTopBar(RouteScreen.HOME.name){

                    scope.launch {
                        scaffoldState.drawerState.open()
            }
            }

        }else if(currentDestination?.hierarchy?.any{
                it.route == RouteScreen.FAVORITES.name } == true){

            MainTopBar(RouteScreen.FAVORITES.name){
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }

        }else if(currentDestination?.hierarchy?.any{
                it.route == RouteScreen.SETTING.name } == true){

            MainTopBar(RouteScreen.SETTING.name){
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }

        } else if(currentDestination?.hierarchy?.any{
                it.route == RouteScreen.DETAILS.name+"/{idCategory}/{idDetails}"
            } == true){
            ChildTopBarMeal(navController,"idCategory")
        }else if(currentDestination?.hierarchy?.any{
        it.route == RouteScreen.MEAL.name+"/{id}/{name}"
            } == true){
            ChildTopBarMeal(navController, key = "name")
        }else if(currentDestination?.hierarchy?.any{
                it.route == RouteScreen.FAVORITES_ITEM.name+"/{id}/{name}"
            } == true){
            ChildTopBarMeal(navController, key ="name")
        }

    }

}