package com.example.weatherapp.feature_weather.presentation.screens.main

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.feature_weather.presentation.navigation.NavigationScreens
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.main.components.bottom_bar.BottomBarCustom
import com.example.weatherapp.feature_weather.presentation.screens.main.components.drawer.DrawerContentCustom
import com.example.weatherapp.feature_weather.presentation.screens.main.components.top_bar.TopBarCustom

@Composable
fun MainScreen(){

    val navController = rememberNavController()
    val state = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        scaffoldState = state,
        backgroundColor = MaterialTheme.colors.surface,
        topBar = {
            TopBarCustom(
                scaffoldState = state,
               navController =  navController
            )
        },
        bottomBar = {
            BottomBarCustom(navController = navController)
        },
        drawerContent = {

                DrawerContentCustom(navController = navController,
                                        scaffoldState =state )
        },
        drawerGesturesEnabled = currentDestination?.hierarchy?.any {
                it.route == "${RouteScreens.DAY.name}/{country}"
                        ||
                        it.route == "${RouteScreens.WEEK.name}/{country}"
            } == true
        ) {


        NavigationScreens(navController)


    }


}