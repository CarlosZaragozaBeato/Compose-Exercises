package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.splash.PromodoroSplahScreen

@Composable
fun PomodoroNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.SPLASH.name){
        composable(route = Routes.SPLASH.name){
            PromodoroSplahScreen(onNavigate ={navController.navigate(it.route)},
                onPopBackStack = {navController.popBackStack()} )
        }
    }
}