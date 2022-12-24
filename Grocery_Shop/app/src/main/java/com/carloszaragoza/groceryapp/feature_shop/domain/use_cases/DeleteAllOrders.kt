package com.carloszaragoza.groceryapp.feature_shop.domain.use_cases

import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import javax.inject.Inject


class DeleteAllOrders @Inject constructor(
    private val repository: OrderRepository
){
    suspend operator fun invoke(){
        repository.deleteAllOrders()
    }

}