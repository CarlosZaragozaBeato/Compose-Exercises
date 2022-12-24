package com.carloszaragoza.groceryapp.feature_shop.presentation.home.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

data class HomeState(
    var user: User?=null,
    var orders: MutableState<Order?> = mutableStateOf(null),
    var randomItems:List<Item> = emptyList())