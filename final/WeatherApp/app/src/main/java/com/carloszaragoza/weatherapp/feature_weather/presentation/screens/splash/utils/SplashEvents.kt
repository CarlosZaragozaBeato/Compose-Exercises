package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash.utils

sealed class SplashEvents{
    object OnNavigate:SplashEvents()
    object OnPop:SplashEvents()
}
