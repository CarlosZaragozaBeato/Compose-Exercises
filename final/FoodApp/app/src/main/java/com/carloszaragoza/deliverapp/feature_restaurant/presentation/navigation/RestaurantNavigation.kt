package com.carloszaragoza.deliverapp.feature_restaurant.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.HomeScreen
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.OrderScreen
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.welcome.WelcomeScreen

@Composable
fun RestaurantNavigation(navController: NavHostController, startDestination: String){
    NavHost(navController = navController, startDestination =startDestination ){
        composable(route = Routes.WELCOME.name){
            WelcomeScreen(navController = navController)
        }
        composable(route = Routes.HOME.name){
            HomeScreen(
                navController = navController
            )
        }
        composable(route = Routes.ORDERS.name){
            OrderScreen(
                pop = {navController.popBackStack()}
            )
        }
    }
}