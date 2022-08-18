package com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryVariantDark,
    secondary = primaryColor,
    onError = redColor
)

private val LightColorPalette = lightColors(
    primary = primaryLight,
    primaryVariant = primaryVariantLight,
    secondary = primaryColor,
    onError = redColor

)

@Composable
fun DeliverAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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