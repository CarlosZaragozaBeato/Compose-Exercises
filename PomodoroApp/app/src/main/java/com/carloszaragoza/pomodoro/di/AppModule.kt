package com.carloszaragoza.pomodoro.di

import android.app.Application
import androidx.room.Room
import com.carloszaragoza.pomodoro.feature_login.data.repository.RepositoryImp
import com.carloszaragoza.pomodoro.feature_login.domain.repository.Repository
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases.*
import com.carloszaragoza.pomodoro.feature_login.data.data_source.PomodorosDatabase
import com.carloszaragoza.pomodoro.feature_login.domain.use_cases.DeletePomodoro
import com.carloszaragoza.pomodoro.feature_login.domain.use_cases.GetPomodoros
import com.carloszaragoza.pomodoro.feature_login.domain.use_cases.PomodoroUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePomodoroDatabase(app: Application): PomodorosDatabase {
        return Room.databaseBuilder(
            app,
            PomodorosDatabase::class.java,
            PomodorosDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePomodoroRepository(db: PomodorosDatabase): Repository {
        return RepositoryImp(db.pomodoroDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCases(repository: Repository): PomodoroUseCases {
        return PomodoroUseCases(
            deletePomodoro =  DeletePomodoro(repository),
            addPomodoro = AddPomodoro(repository),
            getPomodoros = GetPomodoros(repository)
        )
    }
}