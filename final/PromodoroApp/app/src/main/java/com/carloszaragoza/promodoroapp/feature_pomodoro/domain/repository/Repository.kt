package com.carloszaragoza.promodoroapp.feature_pomodoro.domain.repository

import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getPomodoros(): Flow<List<Pomodoro>>

    suspend fun insertPomodoro(pomodoro:Pomodoro)

    suspend fun deletePomodoro(pomodoro:Pomodoro)

    suspend fun getPomodoroById(id:Int): Pomodoro

}