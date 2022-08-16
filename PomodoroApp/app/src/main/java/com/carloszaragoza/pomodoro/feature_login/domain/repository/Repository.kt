package com.carloszaragoza.pomodoro.feature_login.domain.repository

import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPomodoros(): Flow<List<Pomodoro>>

    suspend fun insertPomodoro(pomodoro: Pomodoro)

    suspend fun deletePomodoro(pomodoro:Pomodoro)


}