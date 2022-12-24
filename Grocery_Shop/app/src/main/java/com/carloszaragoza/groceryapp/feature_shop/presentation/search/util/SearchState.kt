package com.carloszaragoza.groceryapp.feature_shop.presentation.search.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order

data class SearchState(
    val listCategories: MutableState<List<Category>> = mutableStateOf(emptyList()),
    val listItems: MutableState<List<Item>> = mutableStateOf(emptyList()),
    val order:MutableState<Order?> = mutableStateOf(null)
)
