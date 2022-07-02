package com.example.areader.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.example.areader.model.Item
import com.example.areader.model.MBook
import com.example.areader.screens.ReaderSplashScreen
import com.example.areader.screens.details.BookDetailsScreen
import com.example.areader.screens.home.HomeScreen
import com.example.areader.screens.home.HomeViewModel
import com.example.areader.screens.login.LoginScreen
import com.example.areader.screens.search.SearchBookViewModelv2
import com.example.areader.screens.search.SearchScreen
import com.example.areader.screens.stats.StatsScreen
import com.example.areader.screens.update.UpdateScreen

@Composable
fun ReaderNavigation () {

    val navController = rememberNavController()

    NavHost(navController =navController,
            startDestination = ReaderScreens.SplashScreen.name){


        composable(route = ReaderScreens.SplashScreen.name ){
            ReaderSplashScreen(navController)
        }

        composable(route = ReaderScreens.ReaderHomeScreen.name ){

            val homeViewModel = hiltViewModel<HomeViewModel>()

            HomeScreen(navController,viewModel = homeViewModel)
        }

        composable(route = ReaderScreens.LoginScreen.name ){
            LoginScreen(navController)
        }

        composable(route = ReaderScreens.SearchScreen.name ){

            val searchViewModel = hiltViewModel<SearchBookViewModelv2>()
            SearchScreen(navController,
                        viewModel = searchViewModel)
        }

        val detailName = ReaderScreens.DetailScreen.name

        composable(route = "$detailName/{bookId}",
        arguments = listOf(navArgument("bookId"){
            type = NavType.StringType })){backStackEntry->

              backStackEntry.arguments?.getString("bookId").let{ book->
                  BookDetailsScreen(navController, bookId = book.toString())
        }


        }

        composable(route = ReaderScreens.StatsScreen.name ){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            StatsScreen(navController, viewModel = homeViewModel)
        }

        val updateName = ReaderScreens.UpdateScreen.name
        composable(route = "$updateName/{bookItemId}",
        arguments = listOf(navArgument("bookItemId"){
            type = NavType.StringType })){backStackEntry->

            backStackEntry.arguments?.getString("bookItemId").let{book->

                UpdateScreen(navController, bookId = book.toString())

            }

        }




    }
}
