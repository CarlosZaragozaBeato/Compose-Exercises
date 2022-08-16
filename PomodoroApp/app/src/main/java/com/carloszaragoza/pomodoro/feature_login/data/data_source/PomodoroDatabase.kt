package com.carloszaragoza.pomodoro.feature_login.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro


@Database(
    entities = [Pomodoro::class],
    version = 5
)
abstract class PomodorosDatabase: RoomDatabase() {
    abstract val pomodoroDao: PomodoroDao

    companion object {
        const val DATABASE_NAME = "pomodoro_db"
    }
}