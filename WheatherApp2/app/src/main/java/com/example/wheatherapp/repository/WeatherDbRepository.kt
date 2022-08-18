package com.example.wheatherapp.repository

import com.example.wheatherapp.data.WeatherDao
import com.example.wheatherapp.model.Favorite
import com.example.wheatherapp.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(
    private val dao: WeatherDao
){

    fun getFavorites(): Flow<List<Favorite>> = dao.getFavorites()

    suspend fun getFavoriteByCity(city: String):Favorite = dao.getFavoriteByCity(city)

    suspend fun insertFavorite(favorite: Favorite) = dao.insertFavorite(favorite)

    suspend fun deleteAllFavorites() = dao.deleteAll()

    suspend fun deleteFavorite(favorite: Favorite) = dao.deleteFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = dao.updateFavorite(favorite)

    fun getUnits(): Flow<List<Unit>> = dao.getUnits()


    suspend fun insertUnit(unit:Unit) = dao.insertUnit(unit)

    suspend fun deleteUnit(unit:Unit) = dao.deleteUnit(unit)

    suspend fun updateUnit(unit:Unit) = dao.updateUnit(unit)

    suspend fun deleteAllUnit() = dao.deleteAllUnit()

}