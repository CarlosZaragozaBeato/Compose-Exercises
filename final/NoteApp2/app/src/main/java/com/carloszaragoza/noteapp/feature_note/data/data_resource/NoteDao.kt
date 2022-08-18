package com.carlos_zaragoza.noteapp.feature_note.data.data_resource

import androidx.room.*
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM note_tbl WHERE id = :id")
    suspend fun getNoteById(id:Int):Note?

    @Query("SELECT * FROM note_tbl")
    fun getNotes(): Flow<List<Note>>
}