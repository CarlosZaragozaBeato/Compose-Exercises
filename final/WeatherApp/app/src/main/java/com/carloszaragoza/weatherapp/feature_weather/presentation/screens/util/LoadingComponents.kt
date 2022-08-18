package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingComponent(){
    Surface{
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent),
        contentAlignment = Alignment.Center) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(.8f),
            color = MaterialTheme.colors.secondary
        )
    }
    }
}