package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.LoadingComponent
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.components.HeaderCard
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.components.WeekBody
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.utils.WeekTimeEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeekTimeScreen(
    viewModel: WeekWeatherViewModel = hiltViewModel(),
    onPop:() -> Unit
){

    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.popBackStack -> onPop()
                else -> Unit
            }
        }
    }
    val weather = viewModel.weatherData.value.data
    val scaffoldState = rememberScaffoldState()

    if(weather == null  || viewModel.isFavorite.value == null){
        LoadingComponent()
    }else{

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(WeekTimeEvents.onPop) }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Downward")
                    }
                },
                title = {
                    Text(viewModel.countryId)
                },
                actions = {
                    IconButton(onClick = {
                        if(!viewModel.isFavorite.value!!){
                            viewModel.onEvent(WeekTimeEvents.onAddFavorite(
                                Favorites(title = weather.location.name)
                            ))
                        }else{
                            viewModel.onEvent(WeekTimeEvents.onDeleteFavorite(
                                Favorites(title = weather.location.name)
                            ))
                        }
                    }) {
                        Icon(imageVector = if(!viewModel.isFavorite.value!!)Icons.Default.FavoriteBorder
                                            else Icons.Default.Favorite ,
                            contentDescription = "Favorite")
                    }
                }
            )
        }
    ){


          Column (
              modifier = Modifier
                  .verticalScroll(rememberScrollState())
                  ) {
              HeaderCard(item = weather)
              WeekBody(item = weather)

          }
      }


    }

}