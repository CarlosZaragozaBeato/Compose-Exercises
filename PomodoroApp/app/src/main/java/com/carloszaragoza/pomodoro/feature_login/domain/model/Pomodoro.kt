package com.carloszaragoza.pomodoro.feature_login.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pomodoro_tbl")
 data class Pomodoro(
    @PrimaryKey

    val name:String,
    val focusTime: Double,
    val color:Int,

)