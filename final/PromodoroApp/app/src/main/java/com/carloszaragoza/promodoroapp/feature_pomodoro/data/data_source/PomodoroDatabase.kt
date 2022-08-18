package com.carloszaragoza.promodoroapp.feature_pomodoro.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro


@Database(
    entities = [Pomodoro::class],
    version = 1
)
abstract class PomodorosDatabase: RoomDatabase() {
    abstract val pomodoroDao: PomodoroDao

    companion object {
        const val DATABASE_NAME = "pomodoro_db"
    }
}