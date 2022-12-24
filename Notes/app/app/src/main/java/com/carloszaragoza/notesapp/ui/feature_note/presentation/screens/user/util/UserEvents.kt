package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.util

sealed class UserEvents{
    data class OnNavigate(val route:String): UserEvents()
    object OnLogOut:UserEvents()
}
