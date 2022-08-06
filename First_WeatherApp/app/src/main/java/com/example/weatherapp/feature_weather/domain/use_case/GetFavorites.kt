package com.example.weatherapp.feature_weather.domain.use_case

import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetFavorites (
    private val repository: Repository
        ){

     operator fun invoke (): Flow<List<Favorites>> {
        return repository.getFavorites()
    }
}