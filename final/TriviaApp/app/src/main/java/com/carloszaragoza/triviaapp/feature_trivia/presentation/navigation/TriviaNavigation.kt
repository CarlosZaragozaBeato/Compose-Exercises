package com.carloszaragoza.triviaapp.feature_trivia.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.HomeScreen
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizScreen
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash.SplashScreen

@Composable
fun TriviaNavigation(navController:NavHostController){

    NavHost(navController = navController, startDestination = Routes.SPLASH.name ){
        composable(route = Routes.SPLASH.name){
            SplashScreen(
                onNavigate = {navController.navigate(it.route)},
                onPopBackStack = {navController.popBackStack()}
            )
        }
        composable(route = Routes.HOME.name){
            HomeScreen(
                onNavigate = {navController.navigate(route = it.route)}
            )
        }
        val routeName = "${Routes.QUIZ.name}/{category}"
        composable(route =routeName, arguments = listOf(
            navArgument(name = "category"){
                type = NavType.StringType
            }
        )){
            BackHandler(true){}
            QuizScreen(onPopBackStack = {navController.popBackStack()},
            onNavigate = {navController.navigate(it.route)})
        }
    }
}