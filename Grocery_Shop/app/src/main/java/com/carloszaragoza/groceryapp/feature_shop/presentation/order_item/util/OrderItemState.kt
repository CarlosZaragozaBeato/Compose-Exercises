package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

data class OrderItemState(
    var currentId:Int = 0,
    var currentUser:MutableState<User?> = mutableStateOf(null),
    var selectedOrder: MutableState<Order?> = mutableStateOf(null),
    var isLoading:Boolean = false,
    var countOrders: Map<String, Int> = (emptyMap()),
    var listItems: MutableState<List<Item>> = mutableStateOf(emptyList()),
    var totalItems: MutableState<Double> = mutableStateOf(0.0)
    )


