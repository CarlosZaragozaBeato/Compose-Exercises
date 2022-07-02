package com.example.notesappudemy.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesappudemy.data.NoteDataBase
import com.example.notesappudemy.data.NoteDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModules {

    @Singleton
    @Provides
    fun provideNotesDao
                (noteDatabase:NoteDataBase):NoteDataBaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): NoteDataBase{
        return Room.databaseBuilder(
                context,
                NoteDataBase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration()
            .build()

    }

}