package com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils

import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

sealed class HomeScreenEvents{
    data class OnSelectPomodoro(val pomodoro: Pomodoro):HomeScreenEvents()
    object StartPomodoro:HomeScreenEvents()
    object PausePomodoro:HomeScreenEvents()
    data class  OnChangePomodoroState(val state:PomodoroState): HomeScreenEvents()
    data class DeletePomodoro(val pomodoro:Pomodoro):HomeScreenEvents()
    object OnAddPomodoro: HomeScreenEvents()
}
