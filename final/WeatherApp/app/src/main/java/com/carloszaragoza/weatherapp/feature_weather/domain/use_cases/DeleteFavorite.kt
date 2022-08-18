package com.carloszaragoza.weatherapp.feature_weather.domain.use_cases

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.domain.repository.Repository


class DeleteFavorite(
    private val repository: Repository
) {
    suspend operator fun invoke(favorite: Favorites){
        repository.deleteFavorite(favorite)
    }

}