package com.carloszaragoza.groceryapp.feature_shop.domain.use_cases

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetOrders @Inject constructor(
    private val repository: OrderRepository
){
     operator fun invoke(): Flow<List<Order>> {
        return repository.getOrders()
    }

}