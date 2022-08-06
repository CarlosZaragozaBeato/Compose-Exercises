package com.example.weatherapp.feature_weather.presentation.screens.main.components.top_bar

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.favorite.FavoriteViewModel
import kotlinx.coroutines.launch

@Composable
fun TopBarCustom(navController: NavHostController, scaffoldState: ScaffoldState){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


        if(currentDestination?.hierarchy?.any{
                it.route == "${RouteScreens.DAY.name}/{country}" ||
                it.route == "${RouteScreens.WEEK.name}/{country}"} == true) {
        BarCustom(navController = navController , scaffoldState = scaffoldState , key ="country" )
        }else if(currentDestination?.hierarchy?.any{
            it.route == RouteScreens.FAVORITES.name} == true) {
            FavoriteTopBar(navController)
        }
    }



@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BarCustom(navController: NavController,scaffoldState:ScaffoldState, key:String,
    viewModel:FavoriteViewModel = hiltViewModel()){


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val scope = rememberCoroutineScope()



    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = {
            Text(text = navBackStackEntry?.arguments?.getString(key).toString(),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.surface,
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Default.Menu ,
                    contentDescription = "Menu Icon" )
            }
        },
        actions = {
            val favorite = viewModel.favorite.collectAsState().value?.title == navBackStackEntry?.arguments?.getString(key).toString()
            viewModel.getFavoriteById(navBackStackEntry?.arguments?.getString(key).toString())

            IconButton(onClick = {

                if(favorite){
                    viewModel.removeFavorite(viewModel.favorite.value!!)
                }else{
                    viewModel.addFavorite(Favorites(title = navBackStackEntry?.arguments?.getString(key).toString()))
                }
                viewModel.getFavoriteById(navBackStackEntry?.arguments?.getString(key).toString())

            }) {
                Icon(imageVector =
                if(favorite)Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Icon")
            }
        })
}

@Composable
fun FavoriteTopBar(navController: NavController){
    TopAppBar(
        title = {
            Text("Favorites")
        },
        navigationIcon = {
            IconButton(onClick = {navController.navigate(RouteScreens.CHOOSE_COUNTRY.name)}) {
                Icon(imageVector = Icons.Default.ArrowBack ,
                    contentDescription = "Arrow Back" )
            }
        }
    )

}