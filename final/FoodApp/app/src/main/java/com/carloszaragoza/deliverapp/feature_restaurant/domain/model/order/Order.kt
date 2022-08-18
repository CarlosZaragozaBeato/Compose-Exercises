package com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_tbl")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val recipeId: String,
    val ubication: String
)