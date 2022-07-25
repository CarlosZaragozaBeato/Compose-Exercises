package com.example.todoapppractice.di

import android.app.Application
import androidx.room.Room
import com.example.todoapppractice.feature_todo.data.data_source.TodoDatabase
import com.example.todoapppractice.feature_todo.data.repository.TodoRepositoryImpl
import com.example.todoapppractice.feature_todo.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDataBase(app: Application):TodoDatabase{
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDatabase):TodoRepository{
        return TodoRepositoryImpl(db.dao)
    }

}