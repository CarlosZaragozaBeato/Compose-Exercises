package com.example.weatherapp.feature_weather.domain.use_case

import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.domain.repository.Repository

class DeleteFavorite(
    private val repository: Repository
) {
    suspend operator fun invoke(favorite: Favorites){
        repository.deleteFavorite(favorite)
    }

}