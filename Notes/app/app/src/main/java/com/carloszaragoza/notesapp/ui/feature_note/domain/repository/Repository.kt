package com.carloszaragoza.notesapp.ui.feature_note.domain.repository

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getNotes(): Flow<List<note_dbItem>>

    suspend fun insertNote(note: note_dbItem)

    suspend fun deleteNote(note: note_dbItem)

    suspend fun getNoteById(id: Int?): note_dbItem?

}