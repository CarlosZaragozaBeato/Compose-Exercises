package com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components.utils

import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

sealed class AddPomodoroEvents{
    data class AddPomodoro(val pomodoro: Pomodoro): AddPomodoroEvents()
    object PopBack: AddPomodoroEvents()
}
