package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import kotlin.math.roundToInt

@Composable
fun WeekBody(
    item: WeekTime
){
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .padding(
                horizontal = LocalSpacing.current.small,
                vertical = LocalSpacing.current.medium
            ),
        verticalArrangement = Arrangement.SpaceAround
    ){
        items(item.forecast.forecastday){ day ->
         Row(
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.CenterVertically,
             modifier = Modifier.fillMaxSize()
         ) {
            Column {
                Text("Date:" , style = TextStyle(
                    fontSize =  MaterialTheme.typography.body2.fontSize,
                    color = MaterialTheme.colors.primaryVariant.copy(.6f)
                ))
                Text(text = day.date.split(" ")[0].replace("-","/"))
            }
             Row {
                 Text(text = day.day.maxtemp_c.roundToInt().toString()+"º",
                     style = TextStyle(
                         color = MaterialTheme.colors.primaryVariant,
                         fontWeight = FontWeight.Bold,
                         fontSize = MaterialTheme.typography.h6.fontSize
                     ))
                 Spacer(modifier = Modifier.width(25.dp))
                 Text(text = day.day.mintemp_c.roundToInt().toString()+"º",
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant.copy(.5f),
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                 )
             }
             Image(painter = rememberAsyncImagePainter(model = "https://"+day.day.condition.icon),
                 contentDescription = "Current weather",
                 modifier = Modifier
                     .size(40.dp))
         }
        }
    }
}