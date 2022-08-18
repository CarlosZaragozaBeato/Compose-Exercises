package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util

import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note


sealed class NotesEvents{

    data class OnNoteClick(val note: Note):NotesEvents()
    object OnAddNoteClick:NotesEvents()
    data class onChangeNoteFilter(val filter:String):NotesEvents()
    object onChangeLayout:NotesEvents()

}
