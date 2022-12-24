package com.carloszaragoza.groceryapp.feature_main.domain.model

import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

data class OrderList(
    var OrderList:MutableList<Order> = mutableListOf()
)