package com.carloszaragoza.pomodoro.feature_login.domain.use_cases

import com.carloszaragoza.pomodoro.feature_login.domain.repository.Repository
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

class DeletePomodoro(
    private val repository: Repository
) {
    suspend operator fun invoke(pomodoro: Pomodoro){
        repository.deletePomodoro(pomodoro)
    }

}