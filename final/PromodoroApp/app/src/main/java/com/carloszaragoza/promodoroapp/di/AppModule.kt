package com.carloszaragoza.promodoroapp.di

import android.app.Application
import androidx.room.Room
import com.carloszaragoza.promodoroapp.feature_pomodoro.data.data_source.PomodorosDatabase
import com.carloszaragoza.promodoroapp.feature_pomodoro.data.repository.RepositoryImp
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.repository.Repository
import com.carloszaragoza.promodoroapp.feature_pomodoro.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            addPomodoro = AddPomodoro(repository),
            deletePomodoro = DeletePomodoro(repository),
            getPomodoros = GetPomodoros(repository),
            getPomodoro = GetPomodoroById(repository),
        )
    }
}