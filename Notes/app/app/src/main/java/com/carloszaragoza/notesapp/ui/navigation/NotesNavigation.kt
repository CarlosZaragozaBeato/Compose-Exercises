package com.carloszaragoza.notesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.access_options.AccessScreen
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login.LoginScreen
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register.RegisterScreen
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.splash.SplashScreen
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.AddEditNoteScreen
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.HomeScreen
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.UserScreen

@Composable
fun NotesNavigation(navController:NavHostController){


    NavHost(navController = navController, startDestination = Routes.SPLASH.name ){
        composable(route = Routes.SPLASH.name){
            SplashScreen(onPop = {navController.popBackStack()}){
                navController.navigate(it)
            }
        }
        composable(route = "${Routes.ACCESS.name}?status={status}", arguments = listOf(
            navArgument(name = "status"){
                type = NavType.StringType
                defaultValue = "-1"
            }
        )){
            AccessScreen { route ->
                navController.navigate(route)
            }
        }

        composable(route = Routes.LOGIN.name ){
            LoginScreen { route ->
                navController.navigate(route.route)
            }
        }

        composable(route = Routes.REGISTER.name ){
            RegisterScreen {
                navController.navigate(it.route)
            }
        }

        composable(route = "${Routes.HOME.name}?status={status}", arguments = listOf(
            navArgument(name = "status"){
                type = NavType.StringType
                defaultValue = "-1"
            }
        )){

            HomeScreen(
                onNavigate = {navController.navigate(it.route)}
            )

    }
        val routeName = "${Routes.ADD_EDIT.name}?noteId={noteId}"
        composable(route = routeName,
            arguments = listOf(
                navArgument(name = "noteId"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )){
            AddEditNoteScreen(
                onPop = {navController.popBackStack()}
            ){
                navController.navigate(it.route)
            }
        }

        composable(route = Routes.USER.name){
            UserScreen(){
                navController.navigate(it.route)
            }
        }
    }
}