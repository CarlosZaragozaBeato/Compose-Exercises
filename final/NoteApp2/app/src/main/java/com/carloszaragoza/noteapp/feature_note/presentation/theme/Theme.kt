package com.carlos_zaragoza.noteapp.feature_note.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = backgroundColorDark,
    primary = primaryColorDark,
    secondary = secondaryColorDark,
    primaryVariant = primaryVariantColorDark
)

private val LightColorPalette = lightColors(
    background = backgroundColorLight,
    primary = primaryColorLight,
    secondary = secondaryColorLight,
    primaryVariant = primaryVariantColorLight


)

@Composable
fun NoteAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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