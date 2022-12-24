package com.carloszaragoza.notesapp.ui.feature_note.data.data_source

import androidx.room.*
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{

    @Query("SELECT * FROM note_tbl")
    fun getNotes(): Flow<List<note_dbItem>>

    @Query("SELECT * FROM note_tbl WHERE id = :id")
    suspend fun getNoteById(id: Int?): note_dbItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: note_dbItem)

    @Delete
    suspend fun deleteNote(note: note_dbItem)
}