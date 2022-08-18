package com.carloszaragoza.weatherapp.feature_weather.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    h1 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 40.sp),

    h2 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 35.sp),

    h3 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 30.sp),

    button = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp)


)