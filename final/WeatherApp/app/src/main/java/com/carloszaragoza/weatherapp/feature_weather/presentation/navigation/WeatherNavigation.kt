package com.carloszaragoza.weatherapp.feature_weather.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.favorites.FavoritesScreen
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.SearchCountryScreen
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash.SplashScreen
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.WeatherScreen
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.WeekTimeScreen


@Composable
fun WeatherNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.SPLASH.name){

        composable(route = Routes.SPLASH.name){
            SplashScreen(
                onNavigate = { navController.navigate(it.route)},
                onPop = {navController.popBackStack()})
        }

        composable(route = Routes.COUNTRY.name){
            BackHandler(true){}
            SearchCountryScreen(onNavigate = {navController.navigate(it.route)})
        }

        val routeDay = Routes.WEATHER.name
        composable("$routeDay/{country}", arguments = listOf(
            navArgument(name = "country"){
                type = NavType.StringType
            }
        )){

            WeatherScreen(onNavigate = {navController.navigate(it.route)},
                onPop = {navController.popBackStack()})
        }


        val routeDay2 = Routes.WEEK_WEATHER.name
        composable("$routeDay2/{week}", arguments = listOf(
            navArgument(name = "week"){
                type = NavType.StringType
            }
        )){

            WeekTimeScreen(onPop = {navController.popBackStack()})
        }
        composable(route = Routes.FAVORITES.name+"/{countryId}", arguments = listOf(
            navArgument(name = "countryId"){
                type = NavType.StringType
            }
        )){
            FavoritesScreen(
                onNavigate = {navController.navigate(it.route)},
                onPop = {navController.popBackStack()}
            )
        }
    }
}