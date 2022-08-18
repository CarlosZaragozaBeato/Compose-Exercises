package com.carloszaragoza.weatherapp.feature_weather.data.repository

import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.domain.repository.Repository
import com.carloszaragoza.weatherapp.feature_weather.data.data_resource.FavoritesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dao: FavoritesDao
): Repository {
    override fun getFavorites(): Flow<List<Favorites>> {
        return dao.getFavorites().flowOn(Dispatchers.IO)
    }

    override suspend fun insertFavorite(favorite: Favorites) {
        dao.insertFavorite(favorite)
    }

    override suspend fun deleteFavorite(favorite: Favorites) {
        dao.deleteFavorite(favorite)
    }

    override suspend fun getFavoriteById(id: String): Favorites {
        return dao.getFavoriteById(id)
    }
}