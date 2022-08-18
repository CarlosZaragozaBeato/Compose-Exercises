package com.carloszaragoza.weatherapp.feature_weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.WeatherNavigation
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                WeatherNavigation(navController = navController)
            }
        }
    }
}

