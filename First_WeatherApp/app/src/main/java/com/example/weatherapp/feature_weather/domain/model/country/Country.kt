package com.example.weatherapp.feature_weather.domain.model.country

data class Country(
    val data: List<Data>,
    val error: Boolean,
    val msg: String
)