package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.favorites.util

sealed class FavoritesEvents{
    data class OnNavigate(val id:String): FavoritesEvents()
    object OnPop:FavoritesEvents()
}
