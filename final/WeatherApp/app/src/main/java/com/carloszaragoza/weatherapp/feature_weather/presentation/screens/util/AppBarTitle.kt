package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun AppBarTitle(
    title:String,
    color: Color,
    weight: FontWeight,
    size: TextUnit
){
    Text(
        title,
        style = TextStyle(
            fontSize = size,
            color = color,
            fontWeight = weight
        )
    )
}