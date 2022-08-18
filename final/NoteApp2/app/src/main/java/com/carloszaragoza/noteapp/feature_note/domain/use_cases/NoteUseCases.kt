package com.carlos_zaragoza.noteapp.feature_note.domain.use_cases

data class NoteUseCases(
    val addNote:AddNote,
    val removeNote:DeleteNote,
    val getNoteById: GetNoteById,
    val getNotes: GetNotes
)
