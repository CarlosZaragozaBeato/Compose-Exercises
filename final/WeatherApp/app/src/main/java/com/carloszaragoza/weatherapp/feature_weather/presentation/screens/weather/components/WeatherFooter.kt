package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime

@Composable
fun WeatherFooter(
    weather : WeekTime
){
    
   LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ){
        items(weather.forecast.forecastday.first().hour){ hour ->


        Card(
            modifier = Modifier
                .size(150.dp)
                .padding(horizontal = LocalSpacing.current.medium),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.primaryVariant.copy(.1f)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = LocalSpacing.current.small),
            ) {
                Text(text = hour.time.split(" ")[1],
                    style = TextStyle(fontWeight = FontWeight.Bold))

                Image(painter = rememberAsyncImagePainter(model = "https://"+hour.condition.icon),
                    contentDescription = "",
                modifier = Modifier
                    .size(55.dp))
                Text(hour.temp_c.toString()+"º",
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.secondary,
                        fontSize = MaterialTheme.typography.h6.fontSize)
                )
            }
        }
        }
    }

}