package com.example.weatherapp.feature_weather.domain.model.actual_day

data class ActualDay(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)