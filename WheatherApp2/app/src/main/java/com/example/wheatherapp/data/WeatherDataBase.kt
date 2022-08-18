package com.example.wheatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wheatherapp.model.Favorite
import com.example.wheatherapp.model.Unit

@Database(
    entities = [Favorite::class, Unit::class],
    exportSchema = false,
    version = 2
)
abstract class WeatherDataBase:RoomDatabase() {
    abstract fun weatherDao():WeatherDao
}