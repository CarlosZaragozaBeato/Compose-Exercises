package com.example.notesappudemy.repository

import com.example.notesappudemy.data.NoteDataBaseDao
import com.example.notesappudemy.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository  @Inject constructor(private val noteDataBaseDao:NoteDataBaseDao){

    suspend fun addNote(note: Note) = noteDataBaseDao.insertNote(note)
    suspend fun removeNote(note: Note) = noteDataBaseDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDataBaseDao.updateNote(note)
    suspend fun deleteAllNotes() = noteDataBaseDao.deleteNotes()
    fun getAllNotes(): Flow<List<Note>> = noteDataBaseDao.getNotes().flowOn(Dispatchers.IO)
        .conflate()
}