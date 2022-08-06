package com.example.weatherapp.feature_weather.domain.model.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "favorites_tbl")
data class Favorites(
    @PrimaryKey
    val title:String,

)
