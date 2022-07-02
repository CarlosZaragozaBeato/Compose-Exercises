package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.home.details.DetailsScreen

@Composable
fun MovieNavigation(){
        val navController = rememberNavController()
        NavHost(navController = navController,
                startDestination = MovieScreens.HOMESCREEN.name){
                    composable(MovieScreens.HOMESCREEN.name){
                        HomeScreen(navController = navController)
                    }
                    composable(MovieScreens.DETAILSSCREEN.name+"/{movie}",
                    arguments = listOf(navArgument(name="movie"){
                        type = NavType.StringType
                    })){
                        backStateEntry->
                        DetailsScreen(navController = navController, backStateEntry.arguments?.getString("movie"))
                    }

                }
}