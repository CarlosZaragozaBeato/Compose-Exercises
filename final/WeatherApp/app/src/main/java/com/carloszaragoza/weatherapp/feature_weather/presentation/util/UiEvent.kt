package com.carloszaragoza.weatherapp.feature_weather.presentation.util

sealed class UiEvent{
    data class onNavigate(val route:String):UiEvent()
    object popBackStack:UiEvent()
}
