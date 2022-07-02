package com.example.notesappudemy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesappudemy.model.Note
import com.example.notesappudemy.util.DateConverter
import com.example.notesappudemy.util.idConverter

@Database(
    entities = [Note::class],
    version  = 1,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    idConverter::class
)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun noteDao():NoteDataBaseDao
}