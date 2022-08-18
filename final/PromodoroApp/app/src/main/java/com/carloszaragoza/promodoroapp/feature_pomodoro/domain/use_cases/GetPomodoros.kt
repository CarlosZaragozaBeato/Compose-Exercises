package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases

import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPomodoros (
    private val repository: Repository
){

    operator fun invoke (): Flow<List<Pomodoro>> {
        return repository.getPomodoros()
    }
}