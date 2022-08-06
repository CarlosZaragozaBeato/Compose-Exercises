package com.example.weatherapp.feature_weather.presentation.theme.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = darkBlue,
    primaryVariant = veryDarkBlue,
    secondary = black,
    surface = blackSurface,
    background = blackBackground,
    onPrimary =   dayColor,
    onSecondary = nightColor
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = lightBlue,
    primaryVariant = veryLightBlue,
    secondary = grayBlue,
    surface = lightSurface,
    background = lightBackground,
    onPrimary =   dayColor,
    onSecondary = nightColor
)

@Composable
fun WeatherAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()){
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}