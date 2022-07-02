package com.example.wheatherapp.data

import androidx.room.*
import com.example.wheatherapp.model.Favorite
import com.example.wheatherapp.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("Select * from fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("Select * from fav_tbl where city = :city")
    suspend fun getFavoriteByCity(city : String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite:Favorite)

    @Query("delete from fav_tbl")
    suspend fun deleteAll()

    //Unit Table
    @Query("Select * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)

    @Delete
    suspend fun deleteUnit(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit:Unit)

    @Query("delete from settings_tbl")
    suspend fun deleteAllUnit()

}