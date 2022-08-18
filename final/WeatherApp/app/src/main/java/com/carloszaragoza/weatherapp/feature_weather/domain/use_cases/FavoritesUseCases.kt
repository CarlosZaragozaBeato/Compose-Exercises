package com.carloszaragoza.weatherapp.feature_weather.domain.use_cases

data class FavoritesUseCases (
    val addFavorite: AddFavorite,
    val deleteFavorite: DeleteFavorite,
    val getFavorites: GetFavorites,
    val getFavoriteById: GetFavoriteById
)