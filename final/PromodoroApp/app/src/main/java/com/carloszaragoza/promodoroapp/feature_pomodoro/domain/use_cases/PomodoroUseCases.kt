package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases

data class PomodoroUseCases (
    val addPomodoro: AddPomodoro,
    val deletePomodoro:DeletePomodoro  ,
    val getPomodoros: GetPomodoros,
    val getPomodoro: GetPomodoroById
)
