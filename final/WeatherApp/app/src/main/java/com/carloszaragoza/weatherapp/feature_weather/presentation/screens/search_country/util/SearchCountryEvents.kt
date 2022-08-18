package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.util

sealed class SearchCountryEvents{
   data class onNavigate(val id:String):SearchCountryEvents()
    data class onChangeCountryFilter(val filter:String):SearchCountryEvents()
}
