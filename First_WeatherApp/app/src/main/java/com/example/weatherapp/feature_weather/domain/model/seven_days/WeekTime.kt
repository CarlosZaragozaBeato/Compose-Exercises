package com.example.weatherapp.feature_weather.domain.model.seven_days

data class WeekTime(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)