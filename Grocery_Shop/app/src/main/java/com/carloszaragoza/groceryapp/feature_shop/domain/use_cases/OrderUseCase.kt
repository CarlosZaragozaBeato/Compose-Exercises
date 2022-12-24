package com.carloszaragoza.groceryapp.feature_shop.domain.use_cases

data class OrderUseCase(
    val addOrder: AddOrder,
    val deleteAllOrders: DeleteAllOrders,
    val deleteOrder: DeleteOrder,
    val getOrders: GetOrders,
    val getOrdersById: GetOrderById
)
