package com.carloszaragoza.pomodoro.feature_login.data.data_source

import androidx.room.*
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

@Dao
interface PomodoroDao {

    @Query("SELECT * FROM pomodoro_tbl")
    fun getPomodoros(): Flow<List<Pomodoro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPomodoro(pomodoro: Pomodoro)

    @Delete
    fun deletePomodoro(pomodoro: Pomodoro)



}