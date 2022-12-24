package com.carloszaragoza.groceryapp.feature_shop.presentation.category.util

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

data class CategoryState(
    val currentCategory: List<Category> = emptyList(),
    val listOfItems: List<Item> = emptyList(),
    val currentCategoryId: String = "",
    var currentOrder: Order? = null
)