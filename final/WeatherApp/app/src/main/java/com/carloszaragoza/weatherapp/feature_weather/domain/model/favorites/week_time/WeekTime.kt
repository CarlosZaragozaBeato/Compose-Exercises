package com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time



data class WeekTime(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)