package com.example.weatherapp.feature_weather.presentation.screens.week.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.feature_weather.domain.model.seven_days.WeekTime
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing

@Composable
fun WeekRowComponent(
    item: WeekTime
){
    LazyRow {
        items(item.forecast.forecastday.size){ index->

            CardWeatherTime(item = item, index =index)

        }
    }

}

@Composable
fun CardWeatherTime(
    item: WeekTime,
    index:Int
){
    val timeWeather = item.forecast.forecastday[index]

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.small, vertical = LocalSpacing.current.medium)
            .height(140.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(timeWeather.date,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSecondary)
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ){
                Image(
                    painter = rememberAsyncImagePainter(model = "https://${timeWeather.day.condition.icon}"),
                    contentDescription = "${timeWeather.day.condition.text} icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp))

                Text("${timeWeather.day.avgtemp_c}ºC",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant)
            }


        }
    }
}