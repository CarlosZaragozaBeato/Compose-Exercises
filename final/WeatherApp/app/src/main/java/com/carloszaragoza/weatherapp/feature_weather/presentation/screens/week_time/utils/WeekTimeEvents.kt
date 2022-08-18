package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.utils

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites

sealed class WeekTimeEvents{
    object onPop: WeekTimeEvents()
    data class onAddFavorite(val favorite: Favorites):WeekTimeEvents()
    data class onDeleteFavorite(val favorite: Favorites): WeekTimeEvents()
}
