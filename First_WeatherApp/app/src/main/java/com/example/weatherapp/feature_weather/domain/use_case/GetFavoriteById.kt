package com.example.weatherapp.feature_weather.domain.use_case

import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.domain.repository.Repository

class GetFavoriteById(
    private val repository: Repository
) {
    suspend operator fun invoke(id: String):Favorites{
       return repository.getFavoriteById(id)
    }

}