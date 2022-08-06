package com.example.weatherapp.feature_weather.presentation.screens.day.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.feature_weather.domain.model.actual_day.ActualDay
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing


@Composable
fun HourRowComponent(
    item:ActualDay
){
    LazyRow(){
        items(item.forecast.forecastday.first().hour.size){ index->

            CardWeatherTime(item = item, index =index)

        }
    }

}

@Composable
fun CardWeatherTime(
    item:ActualDay,
    index:Int
){
    val timeWeather = item.forecast.forecastday.first().hour[index]

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.small, vertical = LocalSpacing.current.medium)
            .height(150.dp)
    ){
       Column(
           modifier = Modifier
               .fillMaxHeight()
               .fillMaxWidth()
               .padding(LocalSpacing.current.small),
           verticalArrangement = Arrangement.SpaceBetween,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           Text(timeWeather.time.split(" ")[1],
               style = MaterialTheme.typography.h6,
               fontWeight = FontWeight.Bold,
               color = if(timeWeather.is_day==1) MaterialTheme.colors.onPrimary
                        else MaterialTheme.colors.onSecondary)
           Column(
               modifier = Modifier
                   .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally,

           ){
               Image(
                   painter = rememberAsyncImagePainter(model = "https://${timeWeather.condition.icon}"),
                     contentDescription = "${timeWeather.condition.text} icon",
                    contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp))

               Text("${timeWeather.temp_c}ºC",
                   style = MaterialTheme.typography.caption,
                   fontWeight = FontWeight.Bold,
                   color = MaterialTheme.colors.primaryVariant)
           }


       }
    }
}