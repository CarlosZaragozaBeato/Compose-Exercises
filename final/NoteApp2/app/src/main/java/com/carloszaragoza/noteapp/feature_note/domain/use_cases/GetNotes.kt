package com.carlos_zaragoza.noteapp.feature_note.domain.use_cases

import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes (
    private val repository: NoteRepository
        ) {
    suspend operator fun invoke(): Flow<List<Note>?>{
        return repository.getNotes()
    }
}