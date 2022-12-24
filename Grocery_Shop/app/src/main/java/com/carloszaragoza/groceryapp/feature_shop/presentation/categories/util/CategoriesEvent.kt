package com.carloszaragoza.groceryapp.feature_shop.presentation.categories.util

sealed class CategoriesEvent{
    data class OnNavigate(val route:String):CategoriesEvent()
    object OnPop:CategoriesEvent()
}
