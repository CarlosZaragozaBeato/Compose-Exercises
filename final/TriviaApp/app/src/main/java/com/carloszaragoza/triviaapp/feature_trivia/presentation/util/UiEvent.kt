package com.carloszaragoza.triviaapp.feature_trivia.presentation.util

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val route:String):UiEvent()
    data class ShowSnackBar(
        val message:String,
        val action:String? = null
    ):UiEvent()
}
