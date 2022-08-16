package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases

import com.carloszaragoza.pomodoro.feature_login.domain.repository.Repository
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

class AddPomodoro (
    private val repository: Repository
){

    suspend operator fun invoke (pomodoro: Pomodoro){
        repository.insertPomodoro(pomodoro)
    }
}
