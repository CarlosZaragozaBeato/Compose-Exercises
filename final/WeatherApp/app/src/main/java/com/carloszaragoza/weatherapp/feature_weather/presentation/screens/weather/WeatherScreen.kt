package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.LoadingComponent
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components.DrawerContent
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components.TopAppBarCustom
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components.WeatherBody
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components.WeatherFooter
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.util.WeatherEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavigate: (UiEvent.onNavigate) -> Unit,
    onPop:() -> Unit
){
    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.onNavigate -> onNavigate(event)
                is UiEvent.popBackStack -> onPop()
            }
        }
    }

    val scaffoldState = rememberScaffoldState()
    val weather = viewModel.weatherData.value.data

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent(countryId = viewModel.countryId){
                viewModel.onEvent(WeatherEvents.onNavigate(it))
            }
        },
        topBar = {
            TopAppBarCustom(
                pop = {viewModel.onEvent(WeatherEvents.onPop)},
                scaffoldState = scaffoldState,
                CountryId = viewModel.countryId,
                navigate = {viewModel.onEvent(WeatherEvents.onNavigate("${Routes.WEEK_WEATHER.name}/$it"))}
            )
        }
    ){

          if(weather != null){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = LocalSpacing.current.large),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherBody(weather = weather)
                Spacer(modifier = Modifier.height(50.dp))
                WeatherFooter(weather = weather)
            }
          }else{
              LoadingComponent()
          }
    }
}

