package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases

import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.repository.Repository

class AddPomodoro (
    private val repository: Repository
){

    suspend operator fun invoke (pomodoro: Pomodoro){
        repository.insertPomodoro(pomodoro)
    }
}
