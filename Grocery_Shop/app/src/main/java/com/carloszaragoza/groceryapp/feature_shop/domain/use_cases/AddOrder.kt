package com.carloszaragoza.groceryapp.feature_shop.domain.use_cases

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import javax.inject.Inject

class AddOrder @Inject constructor(
    private val repository: OrderRepository
){
    suspend operator fun invoke(order: Order){
        repository.insertOrder(order)
    }

}