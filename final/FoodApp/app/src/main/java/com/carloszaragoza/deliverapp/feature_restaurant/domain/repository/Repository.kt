package com.carloszaragoza.deliverapp.feature_restaurant.domain.repository

import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getOrders(): Flow<List<Order>>

    suspend fun insertOrder(order:Order)

    suspend fun deleteOrder(id: Int?)

    suspend fun deleteAll()

    suspend fun getOrderById(id: Int)

}