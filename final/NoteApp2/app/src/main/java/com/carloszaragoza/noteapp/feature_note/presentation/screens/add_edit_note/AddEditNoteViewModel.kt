package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note

import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColor
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.domain.use_cases.NoteUseCases
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note.util.AddEditNoteEvent
import com.carlos_zaragoza.noteapp.feature_note.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor (
    private val useCases:NoteUseCases,
    savedStatedHandle:SavedStateHandle
): ViewModel(){

    var note  = mutableStateOf<Note?>(null)

    var title = mutableStateOf("")

    var description = mutableStateOf("")

    var color = mutableStateOf(Color(0xff3F4E4F))

    var priority = mutableStateOf(Note.Priority.MEDIUM.name)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val suggestions = listOf(Note.Priority.LOW,
        Note.Priority.MEDIUM,
        Note.Priority.HIGH)

    var expanded by  mutableStateOf(false)

    var selectedText by  mutableStateOf("Select priority")

    var textfieldSize by  mutableStateOf(Size.Zero)

    var noteId:Int?;



    init {
         noteId  = savedStatedHandle.get<Int>("noteId")

        viewModelScope.launch {
           val todoId = savedStatedHandle.get<Int>("noteId")
            if(todoId!= -1){
                viewModelScope.launch{
                    useCases.getNoteById(todoId!!).let{
                        title.value = it?.title.toString()
                        description.value = it?.description.toString()
                        color.value = Color(it?.color!!)
                        priority.value = it.priority
                        this@AddEditNoteViewModel.note.value = it
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent){
        when(event){
            is AddEditNoteEvent.OnTitleChange -> {
                title.value = event.title
            }
            is AddEditNoteEvent.OnDescriptionChange -> {
                description.value = event.description
            }
            is AddEditNoteEvent.OnColorChange -> {
                color.value = event.color
            }
            is AddEditNoteEvent.OnPriorityChange -> {
                priority.value = event.priority
            }
            is AddEditNoteEvent.OnSaveNoteClick -> {
                viewModelScope.launch {
                    if(title.value.isBlank()){
                        sedUiEvent(UiEvent.ShowSnackBar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    useCases.addNote(
                       Note(
                           title = title.value,
                           description = description.value,
                           color = color.value.toArgb(),
                           priority = priority.value,
                           id = note.value?.id
                       )
                    )
                    sedUiEvent(UiEvent.PopBackStack)
                }
            }
            is AddEditNoteEvent.OnRemoveNote -> {
                viewModelScope.launch{
                    useCases.removeNote(note.value!!)
                }
            }
        }
    }

    private fun sedUiEvent(event: UiEvent){
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }

}