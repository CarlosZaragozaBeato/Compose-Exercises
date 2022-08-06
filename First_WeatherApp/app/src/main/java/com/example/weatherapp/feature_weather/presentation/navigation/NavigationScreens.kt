package com.example.weatherapp.feature_weather.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.weatherapp.feature_weather.presentation.screens.choose_country.ChooseCountryScreen
import com.example.weatherapp.feature_weather.presentation.screens.day.DayScreen
import com.example.weatherapp.feature_weather.presentation.screens.favorite.FavoriteScreen
import com.example.weatherapp.feature_weather.presentation.screens.splash.SplashScreen
import com.example.weatherapp.feature_weather.presentation.screens.week.WeekScreen

@Composable
fun NavigationScreens(navHostController: NavHostController){

    NavHost(navController = navHostController, startDestination = RouteScreens.SPLASH.name ){
        composable(RouteScreens.SPLASH.name){
            SplashScreen(navController = navHostController)
        }

        composable(RouteScreens.CHOOSE_COUNTRY.name){
            ChooseCountryScreen(navController = navHostController)
        }
        
        val routeDay = RouteScreens.DAY.name
        composable("$routeDay/{country}", arguments = listOf(
            navArgument(name = "country"){
                type = NavType.StringType
            }
        )){navBackStackEntry ->  
            navBackStackEntry.arguments?.getString("country").let{
                DayScreen(country = it)
            }
        }

        val routeWeek = RouteScreens.WEEK.name
        composable("$routeWeek/{country}", arguments = listOf(
            navArgument(name = "country"){
                type = NavType.StringType
            }
        )){navBackStackEntry ->
            navBackStackEntry.arguments?.getString("country").let{
                WeekScreen(country = it)
            }
        }
        
        composable(route = RouteScreens.FAVORITES.name){
            FavoriteScreen(navController = navHostController)
        }
    }

}
