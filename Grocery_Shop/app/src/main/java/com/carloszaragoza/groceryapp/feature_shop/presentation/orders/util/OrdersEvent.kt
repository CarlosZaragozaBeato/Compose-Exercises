package com.carloszaragoza.groceryapp.feature_shop.presentation.orders.util

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

sealed class OrdersEvent{
    object OnPop:OrdersEvent()
    data class OnDelete(val id:String):OrdersEvent()
    object OnCompletedOrder: OrdersEvent()
    object ToggleDialog:OrdersEvent()
    data class AddOrder(val item: Item):OrdersEvent()
}
