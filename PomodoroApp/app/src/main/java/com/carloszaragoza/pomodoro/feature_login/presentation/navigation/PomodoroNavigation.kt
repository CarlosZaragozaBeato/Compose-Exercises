package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home.HomeScreen
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.splash.PromodoroSplahScreen
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.AddPomodoro

@Composable
fun PomodoroNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.HOME.name){
        composable(route = Routes.SPLASH.name){
            PromodoroSplahScreen(onNavigate ={navController.navigate(it.route)},
                onPopBackStack = {navController.popBackStack()} )
        }
        
        composable(route = Routes.HOME.name){
            HomeScreen(onNavigation = {navController.navigate(it.route)})
        }


        composable(route = Routes.ADD.name){
            AddPomodoro(onPop = {navController.popBackStack()})
        }


    }
}