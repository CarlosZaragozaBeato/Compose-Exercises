package com.carloszaragoza.groceryapp.feature_main.presentation.util

sealed class UiEvent{
    data class OnNavigate(val route:String): UiEvent()
    object OnPop:UiEvent()
    data class ShowSnackBar(
        val message:String,
        val action:() -> Unit = {}
    ):UiEvent()
}
