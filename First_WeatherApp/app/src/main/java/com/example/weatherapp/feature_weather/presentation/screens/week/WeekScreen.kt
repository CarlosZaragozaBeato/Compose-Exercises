package com.example.weatherapp.feature_weather.presentation.screens.week

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.feature_weather.presentation.screens.day.components.BodyComponent
import com.example.weatherapp.feature_weather.presentation.screens.day.components.Title
import com.example.weatherapp.feature_weather.presentation.screens.week.components.WeekRowComponent
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing


@Composable
fun WeekScreen(
    country: String?,
    viewModel: WeekViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        viewModel.searchWeather(country!!)
    }
    val data = viewModel.weatherData
    data.value.data?.current?.condition?.icon
    if(data.value.data == null){
        LinearProgressIndicator()
    }else{
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = LocalSpacing.current.medium)
            ){
                Title(country = data.value.data!!.location.country,
                    latitude =  data.value.data!!.location.lat.toString(),
                    longitude =  data.value.data!!.location.lon.toString(),
                    location = data.value.data!!.location.name,
                    region = data.value.data!!.location.region,)



                BodyComponent(
                    image =  data.value.data!!.current.condition.icon,
                    condition = data.value.data!!.current.condition.text,
                    currentTmp = data.value.data!!.current.temp_c.toString(),
                    currentGust = data.value.data!!.current.gust_kph.toString(),
                    feelsLike = data.value.data!!.current.feelslike_c.toString(),
                    Humidity = data.value.data!!.current.humidity.toString(),
                    lastUpdated = data.value.data!!.current.last_updated,
                    Wind = data.value.data!!.current.wind_kph.toString())

                Spacer(modifier = Modifier.height(LocalSpacing.current.extraLarge))

                WeekRowComponent(item = data.value.data!!)
            }
        }
    }

}
