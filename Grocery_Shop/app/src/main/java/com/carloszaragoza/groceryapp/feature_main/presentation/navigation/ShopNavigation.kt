package com.carloszaragoza.groceryapp.feature_main.presentation.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carloszaragoza.groceryapp.feature_login.presentation.login.LoginScreen
import com.carloszaragoza.groceryapp.feature_login.presentation.register.RegisterScreen
import com.carloszaragoza.groceryapp.feature_main.presentation.screens.splash.SplashScreen
import com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome.WelcomeScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.categories.CategoriesScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.CategoryScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.HomeScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.OrderItem
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.OrdersScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.search.SearchScreen
import com.carloszaragoza.groceryapp.feature_shop.presentation.user.UserScreen

@Composable
fun ShopNavigation(
    navController:NavHostController,
    startDestination: String
) {

    NavHost(navController = navController, startDestination = startDestination){
        composable(route = Routes.WELCOME.name){
            WelcomeScreen(navController = navController)
        }
        composable(route = Routes.SPLASH.name){
            SplashScreen(
                onPop = {navController.popBackStack()},
                onNavigation = {navController.navigate(it)}
            )
        }
        composable(route = Routes.LOGIN.name){
            LoginScreen(
                onNavigate = {navController.navigate(it.route)}
            )
        }
        composable(route = Routes.REGISTER.name){
            RegisterScreen(
                onNavigate = {navController.navigate(it.route)}
            )
        }
        composable(route = Routes.HOME.name){
            BackHandler(false){}
            HomeScreen(
                onNavigate = {navController.navigate(it.route)}
            )
        }
        composable(route = Routes.CATEGORIES.name){
            CategoriesScreen(
                onNavigate = {navController.navigate(it.route)},
                onPop = {navController.popBackStack()}
            )
        }
        composable(route = "${Routes.CATEGORY.name}/{category}", arguments = listOf(
            navArgument(name = "category"){
                type = NavType.StringType
            }
        )){
            CategoryScreen(
                onNavigate = {navController.navigate(it.route)}
            )
        }
        composable(route = Routes.ORDERS.name){
            OrdersScreen(onPop= {navController.popBackStack()})
        }
        composable(route = Routes.USER.name){
            UserScreen(onPop = {navController.popBackStack()},
                        onNavigate = {navController.navigate(it.route)})
        }
        composable(route = "${Routes.ITEMS_ORDER.name}/{index}", arguments = listOf(
            navArgument(name = "index"){
                type = NavType.IntType
                defaultValue = -1
            }
        )){
            OrderItem(
                onPop = {navController.popBackStack()}
            )
        }
        composable(route = Routes.SEARCH.name){
            SearchScreen(onNavigate={navController.navigate(it.route)},
                        onPop={navController.popBackStack()})
        }
    }
}