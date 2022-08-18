package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils

import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order

sealed class OrdersEvents{
    object OnPop: OrdersEvents()
    data class Onfilter(val listOrders: List<Order>): OrdersEvents()
    data class OnDeleteItem(val itemId:String): OrdersEvents()
    data class OnAddItem(val order:Order): OrdersEvents()
    object OnCompleteOrders: OrdersEvents()
}
