package com.carloszaragoza.layoutexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carloszaragoza.layoutexample.presentation.screens.details.DetailsScreen
import com.carloszaragoza.layoutexample.presentation.screens.home.HomeScreen


@Composable
fun ScreenNavigation(navController:NavHostController) {

    NavHost(navController = navController, startDestination = Screens.Home.route ){

        composable(route = Screens.Home.route){ HomeScreen(navController) }

        composable(route = "${Screens.Details.route}/foodId={foodId}", arguments = listOf(
            navArgument("foodId"){
                type = NavType.StringType})){
            DetailsScreen()
        }

    }


}