package com.example.notesapp.feature_note.domain.uses_cases

import com.example.notesapp.feature_note.domain.model.Note
import com.example.notesapp.feature_note.domain.repository.NoteRepository

class DeleteNote (
        private val repository: NoteRepository
        ){
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}