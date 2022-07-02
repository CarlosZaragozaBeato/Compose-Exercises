package com.example.wheatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wheatherapp.screens.WeatherMainScreen
import com.example.wheatherapp.screens.WeatherSplashScreen
import com.example.wheatherapp.screens.about.AboutScreen
import com.example.wheatherapp.screens.favorite.FavoriteScreen
import com.example.wheatherapp.screens.main.MainViewModel
import com.example.wheatherapp.screens.search.SearchScreen
import com.example.wheatherapp.screens.settings.SettingsScreen

@Composable
fun WeatherNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SPLASH_SCREEN.name){
            composable(Screens.SPLASH_SCREEN.name){
                WeatherSplashScreen(navController = navController )
            }

        val route = Screens.MAIN_SCREEN.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city"){
                    type = NavType.StringType
                })){ navBack ->
            navBack.arguments?.getString("city").let { city ->

                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController, mainViewModel,
                    city = city)
            }


        }
        composable(Screens.ABOUT_SCREEN.name){
            AboutScreen(navController = navController)
        }

        composable(Screens.FAVORITE_SCREEN.name){
            FavoriteScreen(navController = navController)
        }

        composable(Screens.SETTINGS_SCREEN.name){
            SettingsScreen(navController = navController)
        }


    }
}
