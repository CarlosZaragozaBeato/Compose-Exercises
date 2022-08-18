package com.carlos_zaragoza.noteapp.feature_note.domain.use_cases

import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.domain.repository.NoteRepository

class GetNoteById (
    private val repository: NoteRepository
        ) {
    suspend operator fun invoke(id: Int): Note?{
        return repository.getNoteById(id)
    }
}