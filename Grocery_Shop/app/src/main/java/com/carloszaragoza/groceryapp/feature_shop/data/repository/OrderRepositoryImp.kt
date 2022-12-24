package com.carloszaragoza.groceryapp.feature_shop.data.repository

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.data.data_source.OrdersDao
import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepositoryImp @Inject constructor(
    private val dao:OrdersDao
):OrderRepository{
    override fun getOrders(): Flow<List<Order>> {
        return dao.getOrders()
    }

    override suspend fun insertOrder(order: Order) {
        dao.insertOrder(order)
    }

    override suspend fun deleteOrder(order: Order) {
        dao.deleteOrder(order)
    }

    override suspend fun getOrderById(id: Int?): Order? {
        return dao.getOrderById(id)
    }

    override suspend fun deleteAllOrders() {
        dao.deleteAllOrders()
    }


}