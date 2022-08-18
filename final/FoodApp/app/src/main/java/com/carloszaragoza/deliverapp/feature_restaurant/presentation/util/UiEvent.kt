package com.carloszaragoza.deliverapp.feature_restaurant.presentation.util

sealed class UiEvent{
    data class onNavigation(val route:String):UiEvent()
    object onPopBackStack:UiEvent()
}
