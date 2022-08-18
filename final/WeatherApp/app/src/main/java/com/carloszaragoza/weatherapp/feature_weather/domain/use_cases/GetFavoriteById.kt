package com.carloszaragoza.weatherapp.feature_weather.domain.use_cases

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.domain.repository.Repository


class GetFavoriteById(
    private val repository: Repository
) {
    suspend operator fun invoke(id: String): Favorites {
       return repository.getFavoriteById(id)
    }

}