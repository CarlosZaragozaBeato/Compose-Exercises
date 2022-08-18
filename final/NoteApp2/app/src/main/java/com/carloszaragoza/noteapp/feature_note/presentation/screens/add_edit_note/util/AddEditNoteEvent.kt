package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note.util

import androidx.compose.ui.graphics.Color
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents

sealed class AddEditNoteEvent{
    data class OnTitleChange(val title:String):AddEditNoteEvent()
    data class OnDescriptionChange(val description :String):AddEditNoteEvent()
    data class OnPriorityChange(val priority: String):AddEditNoteEvent()
    data class OnColorChange(val color: Color):AddEditNoteEvent()
    object OnSaveNoteClick:AddEditNoteEvent()
    object OnRemoveNote:AddEditNoteEvent()
}
