package com.carloszaragoza.pomodoro.feature_login.domain.use_cases

import com.carloszaragoza.pomodoro.feature_login.domain.repository.Repository
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

class GetPomodoros (
    private val repository: Repository
){

    operator fun invoke (): Flow<List<Pomodoro>> {
        return repository.getPomodoros()
    }
}