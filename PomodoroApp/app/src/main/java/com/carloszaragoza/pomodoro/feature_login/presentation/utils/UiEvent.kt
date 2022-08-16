package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils

sealed class UiEvent{
    data class onNavigate(val route:String):UiEvent()
    object popBackStack:UiEvent()
}
