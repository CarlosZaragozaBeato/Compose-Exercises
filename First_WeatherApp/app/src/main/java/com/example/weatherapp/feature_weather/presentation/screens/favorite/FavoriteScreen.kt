package com.example.weatherapp.feature_weather.presentation.screens.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.feature_weather.presentation.screens.favorite.components.RowCard
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing
import com.example.weatherapp.feature_weather.presentation.utils.LoadingComponent

@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
){

    val favorites = viewModel.listOfFavorites
    LaunchedEffect(key1 = true){
        viewModel.getFavorites()
    }

    if(favorites.value.isNullOrEmpty()) {
        LoadingComponent()
    }else{
        Column(
            modifier = Modifier
                .padding(LocalSpacing.current.medium)
        ){
            Text("Favorites",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
            )
            LazyColumn {
                items(viewModel.listOfFavorites.value?.size!!){ index->
                    RowCard(favorite = viewModel.listOfFavorites.value!![index],
                        navController = navController)
                }
            }
        }
    }


}