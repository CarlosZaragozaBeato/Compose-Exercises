package com.carloszaragoza.groceryapp.feature_shop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_tbl")
data class Order(
    @PrimaryKey
    val id:Int?= null,
    val userId:Int?=null,
    val itemsList: ItemsList?=null,
    val date: String?=null
)