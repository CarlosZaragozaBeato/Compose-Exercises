package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
fun HeaderCard(
    item: WeekTime
){

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.small, vertical = LocalSpacing.current.medium)
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(start = LocalSpacing.current.small,
                    top = LocalSpacing.current.small,
                    end = LocalSpacing.current.small,
                    bottom = LocalSpacing.current.medium)
        ) {
            Image(painter = rememberAsyncImagePainter(model = "https://"+item.current.condition.icon),
                contentDescription = "Current weather",
                modifier = Modifier
                    .size(70.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    ){

                Text(item.current.condition.text,
                    style = TextStyle(
                        color = MaterialTheme.colors.secondary,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold
                    ))
                Text("last updated : ${item.current.last_updated}",
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = MaterialTheme.typography.body1.fontSize
                    )
                )
            }
        }
    }


}