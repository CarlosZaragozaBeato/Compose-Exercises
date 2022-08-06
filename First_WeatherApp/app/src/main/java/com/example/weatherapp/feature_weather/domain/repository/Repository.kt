package com.example.weatherapp.feature_weather.domain.repository

import com.example.weatherapp.feature_weather.data.data_resource.FavoritesDao
import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository  {

    fun getFavorites(): Flow<List<Favorites>>

    suspend fun insertFavorite(favorite:Favorites)

    suspend fun deleteFavorite(favorite:Favorites)

    suspend fun getFavoriteById(id:String):Favorites
}