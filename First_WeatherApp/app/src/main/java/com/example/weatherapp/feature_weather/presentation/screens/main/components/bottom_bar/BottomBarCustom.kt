package com.example.weatherapp.feature_weather.presentation.screens.main.components.bottom_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.feature_weather.domain.model.bottom_item.BottomItem
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.main.components.bottom_bar.components.AddItem

@Composable
fun BottomBarCustom(navController: NavController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screens = listOf(
        BottomItem (
            title = "Week Time",
            route = "${RouteScreens.WEEK.name}/${navBackStackEntry?.arguments?.getString("country")}",
            selected = "${RouteScreens.WEEK.name}/{country}",
            icon = Icons.Filled.Cloud,
        ),

        BottomItem (
            route = "${RouteScreens.DAY.name}/${navBackStackEntry?.arguments?.getString("country")}",
            selected = "${RouteScreens.DAY.name}/{country}",
            title = "Actual Time",
            icon = Icons.Filled.Cloud)
    )

    if(currentDestination?.hierarchy?.any{
            it.route == "${RouteScreens.DAY.name}/{country}" || it.route == "${RouteScreens.WEEK.name}/{country}"
        } == true){
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface
        ) {
            screens.forEach{ item ->
                AddItem(screen = item, navController = navController,
                    currentDestination = currentDestination)
            }
        }
    }
}