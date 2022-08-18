package com.example.notesapp.feature_note.domain.uses_cases

data class NoteUseCases (
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
        )

