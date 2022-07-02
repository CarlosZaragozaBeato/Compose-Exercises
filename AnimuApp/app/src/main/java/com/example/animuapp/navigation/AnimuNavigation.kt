package com.example.animuapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animuapp.screens.details.DetailsPage
import com.example.animuapp.screens.home.HomeScreen


@Composable
fun AnimuNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AnimusScreens.HOMESCREEN.name){
            composable(AnimusScreens.HOMESCREEN.name){
                HomeScreen(navController = navController)
            }
            composable(AnimusScreens.DETAILSSCREEN.name+"/{animu}",
            arguments = listOf(navArgument(name = "animu"){
                type = NavType.StringType
            })){
                backStateEntry->
                DetailsPage(navController = navController, backStateEntry.arguments?.getString("animu"))
            }
    }


}