package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util

sealed class AddEditEvents{

    object OnSaveNote:AddEditEvents()
    data class OnColorChange(val color: Int):AddEditEvents()
    data class OnTitleChange(val title:String):AddEditEvents()
    data class OnDescriptionChange(val description:String):AddEditEvents()
    data class OnPriorityChange(val priority: String):AddEditEvents()
    object OnPop:AddEditEvents()

}
