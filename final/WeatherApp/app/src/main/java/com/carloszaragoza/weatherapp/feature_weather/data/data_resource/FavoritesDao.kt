package com.carloszaragoza.weatherapp.feature_weather.data.data_resource

import androidx.room.*
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao{

    @Query("SELECT * FROM favorites_tbl")
    fun getFavorites(): Flow<List<Favorites>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorites)

    @Delete
    fun deleteFavorite(favorite: Favorites)

    @Query("SELECT * FROM favorites_tbl WHERE title == :id")
    fun getFavoriteById(id:String):Favorites
}