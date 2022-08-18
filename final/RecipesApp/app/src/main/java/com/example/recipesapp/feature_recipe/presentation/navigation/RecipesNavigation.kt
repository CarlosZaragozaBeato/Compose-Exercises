package com.example.recipesapp.feature_recipe.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipesapp.feature_recipe.presentation.screens.category_details.CategoryScreen
import com.example.recipesapp.feature_recipe.presentation.screens.favorites.FavoriteScreen
import com.example.recipesapp.feature_recipe.presentation.screens.favorites.favorite_item.FavoriteItemScreen
import com.example.recipesapp.feature_recipe.presentation.screens.home.HomeScreen
import com.example.recipesapp.feature_recipe.presentation.screens.meal.MealScreen
import com.example.recipesapp.feature_recipe.presentation.screens.settings.SettingsScreen

@Composable
fun RecipesNavigation(navController: NavHostController){


    NavHost(navController = navController, startDestination = RouteScreen.HOME.name){
        //*Hpme
        composable(route =  RouteScreen.HOME.name){
            HomeScreen(navController = navController)
        }

        //*Favorite
        composable(route= RouteScreen.FAVORITES.name){
            FavoriteScreen(navController = navController)
        }

        //*Category
        val categoriesName = RouteScreen.DETAILS.name
        composable(route = "${categoriesName}/{idCategory}/{idDetails}", arguments = listOf(
            navArgument(name = "idCategory"){
                type = NavType.StringType
            },
            navArgument(name = "idDetails"){
                type = NavType.StringType
            },
        )){ backStackEntry->
            val category = backStackEntry.arguments?.getString("idCategory")
            val id = backStackEntry.arguments?.getString("idDetails")
            CategoryScreen(navController = navController,
                category = category,
                id = id )
        }

        //*Meal
        val mealScreen = RouteScreen.MEAL.name
        composable("${mealScreen}/{id}/{name}", arguments = listOf(
            navArgument(name = "id"){
                type = NavType.StringType
            },
            navArgument(name = "name"){
                type = NavType.StringType
            }
        )){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val name = backStackEntry.arguments?.getString("name")

            MealScreen(id =id, title =name)
        }

        //*FavoriteItem
        val favoriteItemScreen = RouteScreen.FAVORITES_ITEM.name
        composable("${favoriteItemScreen}/{id}/{name}", arguments = listOf(
            navArgument(name = "id"){
                type = NavType.StringType
            },
            navArgument(name = "name"){
                type = NavType.StringType
            }
        )){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val name = backStackEntry.arguments?.getString("name")

            FavoriteItemScreen(navController = navController, id =id , title =name )
        }

        //*SettingScreen
        composable(route = RouteScreen.SETTING.name){
            SettingsScreen(navController)
        }
    }
}



