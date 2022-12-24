package com.carloszaragoza.groceryapp.feature_shop.presentation.user.util

sealed class UserEvents{
    object OnLogOut:UserEvents()
    object OnPop:UserEvents()
    data class OnNavigate(val index: Int): UserEvents()
}
