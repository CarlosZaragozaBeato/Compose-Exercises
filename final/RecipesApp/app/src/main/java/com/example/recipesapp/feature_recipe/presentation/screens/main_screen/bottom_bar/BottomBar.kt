package com.example.recipesapp.feature_recipe.presentation.screens.main_screen.bottom_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipesapp.feature_recipe.domain.model.bottom_item.BottomItem
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.bottom_bar.components.AddItem
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.util.Colors


@Composable
fun BottomBar(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf<BottomItem>(
        BottomItem.Home,
        BottomItem.Favorites,
    )

    if(currentDestination?.hierarchy?.any{
            it.route == RouteScreen.HOME.name || it.route == RouteScreen.FAVORITES.name
        } == true){
        BottomNavigation(
            backgroundColor = Colors.redPastel
        ) {
            screens.forEach{ item ->
                AddItem(screen = item, navController =navController,
                    currentDestination = currentDestination)
            }
        }
    }
}