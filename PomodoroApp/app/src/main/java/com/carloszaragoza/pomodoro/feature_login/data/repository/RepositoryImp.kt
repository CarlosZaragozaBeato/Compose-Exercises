package com.carloszaragoza.pomodoro.feature_login.data.repository


import com.carloszaragoza.pomodoro.feature_login.domain.repository.Repository
import com.carloszaragoza.pomodoro.feature_login.data.data_source.PomodoroDao
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dao: PomodoroDao
): Repository {
    override fun getPomodoros(): Flow<List<Pomodoro>> {
            return dao.getPomodoros()
    }

    override suspend fun insertPomodoro(pomodoro: Pomodoro) {
            dao.insertPomodoro(pomodoro)
    }

    override suspend fun deletePomodoro(pomodoro: Pomodoro) {
        dao.deletePomodoro(pomodoro)
    }


}