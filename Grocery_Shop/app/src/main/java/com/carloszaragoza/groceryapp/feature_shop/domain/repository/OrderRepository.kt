package com.carloszaragoza.groceryapp.feature_shop.domain.repository

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrders(): Flow<List<Order>>

    suspend fun insertOrder(order: Order)

    suspend fun deleteOrder(order: Order)

    suspend fun getOrderById(id:Int?): Order?

    suspend fun deleteAllOrders()
}