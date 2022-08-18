package com.carloszaragoza.triviaapp.feature_trivia.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = darkPrimary,
    secondary = darkPrimaryVariant,
    surface = surfaceColor,
    onPrimary = mainColor

)

private val LightColorPalette = lightColors(
    primary = lightPrimaryVariant,
    secondary =  lightPrimary,
    surface = surfaceColor,
    onPrimary = mainColor

)

@Composable
fun TriviaAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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