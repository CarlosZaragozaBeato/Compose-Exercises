package com.carloszaragoza.pomodoro.feature_login.domain.use_cases

import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases.AddPomodoro


data class PomodoroUseCases (
    val addPomodoro: AddPomodoro,
    val deletePomodoro: DeletePomodoro,
    val getPomodoros: GetPomodoros,

    )
