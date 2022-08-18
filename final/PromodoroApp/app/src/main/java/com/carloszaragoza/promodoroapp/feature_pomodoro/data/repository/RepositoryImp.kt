package com.carloszaragoza.promodoroapp.feature_pomodoro.data.repository

import com.carloszaragoza.promodoroapp.feature_pomodoro.data.data_source.PomodoroDao
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dao: PomodoroDao
): Repository {
    override fun getPomodoros(): Flow<List<Pomodoro>> {
        return dao.getPomodoros().flowOn(Dispatchers.IO)
    }

    override suspend fun insertPomodoro(pomodoro: Pomodoro) {
        dao.insertPomodoro(pomodoro)
    }

    override suspend fun deletePomodoro(pomodoro: Pomodoro) {
        dao.deletePomodoro(pomodoro)
    }

    override suspend fun getPomodoroById(id: Int): Pomodoro {
        return dao.getPomodoroById(id)
    }
}