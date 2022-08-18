package com.carlos_zaragoza.noteapp.feature_note.data.data_resource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 10
)

abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_database"
    }
}