package com.example.weatherapp.feature_weather.domain.use_case

data class FavoritesUseCases (
        val addFavorite: AddFavorite,
        val deleteFavorite: DeleteFavorite,
        val getFavorites:GetFavorites,
        val getFavoriteById:GetFavoriteById)