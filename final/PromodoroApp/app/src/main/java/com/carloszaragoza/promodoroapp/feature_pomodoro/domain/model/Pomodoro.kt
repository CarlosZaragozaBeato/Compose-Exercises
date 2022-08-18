package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promo_tbl")
data class Pomodoro(

    val name:String,
    val focusTime: Int,
    val breakTime:Int?,
    val longBreak:Int?,
    val setOfPromos:Int?,
    val color:Int,

    @PrimaryKey
    val id:Int? = 0,

)
