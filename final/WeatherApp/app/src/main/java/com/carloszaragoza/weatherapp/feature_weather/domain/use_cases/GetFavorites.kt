package com.carloszaragoza.weatherapp.feature_weather.domain.use_cases

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetFavorites (
    private val repository: Repository
        ){

     operator fun invoke (): Flow<List<Favorites>> {
        return repository.getFavorites()
    }
}