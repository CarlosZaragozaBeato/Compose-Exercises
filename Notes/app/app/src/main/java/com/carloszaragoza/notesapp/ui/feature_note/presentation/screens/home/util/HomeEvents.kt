package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem

sealed class HomeEvents {
    data class TextFieldChange(val text:String):HomeEvents()
    object ChangeLayout: HomeEvents()
    data class OnDeleteNote(val note: note_dbItem):HomeEvents()
    data class OnNavigate(val route:String): HomeEvents()
    data class AddNote(val note: note_dbItem):HomeEvents()
}