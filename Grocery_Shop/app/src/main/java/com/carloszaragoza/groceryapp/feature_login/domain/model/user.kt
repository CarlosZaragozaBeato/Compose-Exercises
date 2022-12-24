package com.carloszaragoza.groceryapp.feature_main.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class User(
    @PrimaryKey
    val id:Int? = null,
    val username:String,
    val password:String,
    val logIn:Int,
    val orderList: OrderList)