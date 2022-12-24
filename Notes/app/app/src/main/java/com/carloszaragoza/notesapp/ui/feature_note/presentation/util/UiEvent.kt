package com.carloszaragoza.notesapp.ui.feature_note.presentation.util

sealed class UiEvent{
    data class Navigate(val route:String):UiEvent()
    object OnPop:UiEvent()
    data class ShowSnackBar(val message:String):UiEvent()

}
