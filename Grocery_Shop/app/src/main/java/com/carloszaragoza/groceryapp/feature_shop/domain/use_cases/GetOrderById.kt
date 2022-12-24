package com.carloszaragoza.groceryapp.feature_shop.domain.use_cases

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import javax.inject.Inject


class GetOrderById @Inject constructor(
    private val repository: OrderRepository
){
    suspend operator fun invoke(id:Int): Order? {
        return repository.getOrderById(id)
    }

}