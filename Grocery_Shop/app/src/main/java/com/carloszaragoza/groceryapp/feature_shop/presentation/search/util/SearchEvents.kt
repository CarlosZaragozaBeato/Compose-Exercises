package com.carloszaragoza.groceryapp.feature_shop.presentation.search.util

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

sealed class SearchEvents{
    data class OnNavigate(val route:String):SearchEvents()
    object OnPop: SearchEvents()
    data class OnChangeFilerText(val text:String):SearchEvents()
    data class OnAddItem(val item: Item): SearchEvents()
}