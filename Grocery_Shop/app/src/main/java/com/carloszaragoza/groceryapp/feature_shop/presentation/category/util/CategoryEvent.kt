package com.carloszaragoza.groceryapp.feature_shop.presentation.category.util

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

sealed class CategoryEvent {
    data class OnNavigate(val route:String): CategoryEvent()
    data class AddItem(val item: Item):CategoryEvent()
}