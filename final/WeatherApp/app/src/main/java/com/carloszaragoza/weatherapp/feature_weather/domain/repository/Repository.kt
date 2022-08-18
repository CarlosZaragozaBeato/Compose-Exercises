package com.carloszaragoza.weatherapp.feature_weather.domain.repository

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import kotlinx.coroutines.flow.Flow

interface Repository  {

    fun getFavorites(): Flow<List<Favorites>>

    suspend fun insertFavorite(favorite:Favorites)

    suspend fun deleteFavorite(favorite:Favorites)

    suspend fun getFavoriteById(id:String):Favorites
}