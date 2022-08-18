package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime

@Composable
fun WeatherBody(
    weather: WeekTime
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://" + weather.current.condition.icon),
                contentDescription = "Content",
                modifier = Modifier
                    .size(100.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weather.current.condition.text,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weather.current.temp_c.toString() + " º",
                style = TextStyle(fontSize = MaterialTheme.typography.h2.fontSize,
                fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                Row(  verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = "Water Drop Icon",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(text = weather.current.humidity.toString() + "%",
                        style = TextStyle(fontSize = 13.sp))
                }

                Spacer(modifier = Modifier.width(30.dp))

                Row(  verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Air,
                        contentDescription = "Wind Icon",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(text = weather.current.wind_kph.toString() + " kmh/h",
                        style = TextStyle(fontSize = 13.sp)
                    )
                }
            }
        }
    }
}