package com.carloszaragoza.groceryapp.feature_shop.presentation.home.util

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

sealed class HomeEvents{
    data class OnNavigate(val route:String):HomeEvents()
    data class AddItem(val item: Item): HomeEvents()
}
