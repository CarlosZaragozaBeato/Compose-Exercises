package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.util

sealed class WeatherEvents{
    data class onNavigate(val id:String): WeatherEvents()
    object onPop:WeatherEvents()
}
