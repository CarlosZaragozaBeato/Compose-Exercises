package com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites_tbl")
data class Favorites(
    @PrimaryKey
    val title:String,

)
