package com.example.weatherapp.feature_weather.data.data_resource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites


@Database(
    entities = [Favorites::class],
    version = 1
)
abstract class FavoritesDatabase:RoomDatabase() {
    abstract val favoritesDao:FavoritesDao

    companion object {
        const val DATABASE_NAME = "favorites_db"
    }
}