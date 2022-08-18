package com.carloszaragoza.promodoroapp.feature_pomodoro.data.data_source

import androidx.room.*
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.model.Pomodoro
import kotlinx.coroutines.flow.Flow

@Dao
interface PomodoroDao {

    @Query("SELECT * FROM promo_tbl")
    fun getPomodoros(): Flow<List<Pomodoro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPomodoro(favorite: Pomodoro)

    @Delete
    fun deletePomodoro(favorite: Pomodoro)

    @Query("SELECT * FROM promo_tbl WHERE id == :id")
    fun getPomodoroById(id:Int):Pomodoro
}