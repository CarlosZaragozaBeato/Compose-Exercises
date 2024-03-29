package com.carlos_zaragoza.noteapp.di

import android.app.Application
import androidx.room.Room
import com.carlos_zaragoza.noteapp.feature_note.data.data_resource.NoteDatabase
import com.carlos_zaragoza.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.carlos_zaragoza.noteapp.feature_note.domain.repository.NoteRepository
import com.carlos_zaragoza.noteapp.feature_note.domain.use_cases.*
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
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db:NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            addNote = AddNote(repository),
            removeNote = DeleteNote(repository),
            getNoteById = GetNoteById(repository),
            getNotes = GetNotes(repository)
        )
    }

}