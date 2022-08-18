package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing

@Composable
fun DrawerHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary),

        ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontSize = MaterialTheme.typography.h1.fontSize,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold),) {
                    append("Weather")
                }
                withStyle(style = SpanStyle(
                    fontSize = MaterialTheme.typography.h1.fontSize,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Light)
                ) {
                    append(" App")
                }
            },
            modifier = Modifier
                .padding(vertical = LocalSpacing.current.large
                    , horizontal = LocalSpacing.current.medium)
        )
    }
}