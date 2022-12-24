package com.carloszaragoza.groceryapp.feature_shop.presentation.orders.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order


data class OrdersState(
    val order: Order? = null,
    var countOrders: Map<String, Int> = (emptyMap()),
    var listItems: MutableState<List<Item>> = mutableStateOf(emptyList()),
    var totalItems: MutableState<Double> = mutableStateOf(0.0),
    var delivery: MutableState<Double> = mutableStateOf(0.0),
    var total:MutableState<Double> = mutableStateOf(0.0),
    var isLoading: Boolean = false,
    val completeOrder:Boolean = false,
    val showDialog:MutableState<Boolean> = mutableStateOf(false)
)