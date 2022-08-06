package com.example.weatherapp.feature_weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.feature_weather.presentation.navigation.NavigationScreens
import com.example.weatherapp.feature_weather.presentation.screens.main.MainScreen
import com.example.weatherapp.feature_weather.presentation.screens.splash.SplashScreen
import com.example.weatherapp.feature_weather.presentation.theme.ui.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                MainScreen()
            }
        }
    }
}

