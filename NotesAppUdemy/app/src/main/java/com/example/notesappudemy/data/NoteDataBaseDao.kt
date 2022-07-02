package com.example.notesappudemy.data

import androidx.compose.runtime.MutableState
import androidx.room.*
import com.example.notesappudemy.model.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDataBaseDao {

    @Query("Select * from notes_tbl")
    fun getNotes (): Flow<List<Note>>

    @Query("Select * from notes_tbl where id = :id")
    suspend fun getNoteById (id:String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertNote(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note:Note)

    @Query("Delete  from notes_tbl")
    suspend fun deleteNotes()

    @Delete
    suspend fun deleteNote(note:Note)

}
