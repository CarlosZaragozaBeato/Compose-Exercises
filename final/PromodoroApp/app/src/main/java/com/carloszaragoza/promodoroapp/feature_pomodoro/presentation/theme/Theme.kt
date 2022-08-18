package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkPrimary,
    primaryVariant = darkPrimaryVariant,
    secondary = Secondary
)

private val LightColorPalette = lightColors(
    primary = lightPrimary,
    primaryVariant = lightPrimaryVariant,
    secondary = Secondary

)

@Composable
fun PromodoroAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}