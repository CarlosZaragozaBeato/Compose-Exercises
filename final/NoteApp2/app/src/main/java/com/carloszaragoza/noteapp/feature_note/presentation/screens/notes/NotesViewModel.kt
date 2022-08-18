package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note.Priority
import com.carlos_zaragoza.noteapp.feature_note.domain.repository.NoteRepository
import com.carlos_zaragoza.noteapp.feature_note.domain.use_cases.NoteUseCases
import com.carlos_zaragoza.noteapp.feature_note.presentation.navigation.Routes
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents
import com.carlos_zaragoza.noteapp.feature_note.presentation.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases:NoteUseCases,
    private val repository: NoteRepository
): ViewModel() {

    private val _notes = MutableStateFlow<List<Note?>>(emptyList())
    val notes = _notes.asStateFlow()



    private var _listFiltered = MutableStateFlow<List<Note?>>(emptyList())
    val listFiltered = _listFiltered.asStateFlow()


    private var deletedNote: Note? = null

    val searchByTitle =  mutableStateOf("")

    val changeLayout = mutableStateOf(false)

    init {
        viewModelScope.launch {
            useCases.addNote(
                note = Note(
                    title="What is Lorem Ipsum?",
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    color = Color(0xff774360).toArgb(),
                    priority = Priority.LOW.name
                )
            )
            useCases.addNote(
                note = Note(
                    title="What is Lorem Ipsum?",
                    description = "Lorem I has bewith the release of Letrp publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    color = Color(0xff774360).toArgb(),
                    priority = Priority.LOW.name
                )
            )
            useCases.addNote(
                note = Note(
                    title="What is Lorem Ipsum?",
                    description = "Lorem I has bewith the release of Letrp publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    color = Color(0xff774360).toArgb(),
                    priority = Priority.LOW.name
                )
            )
            useCases.addNote(
                note = Note(
                    title="What is Lorem Ipsum?",
                    description = "Lorem I has bewith the release of Letrp publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    color = Color(0xff774360).toArgb(),
                    priority = Priority.LOW.name
                )
            )


            repository.getNotes().collect{
                _notes.value = it
            }
        }
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()



    fun onEvent(event: NotesEvents){
        when(event){

            is NotesEvents.OnNoteClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE.name+"?noteId=${event.note.id}"))
            }

            is NotesEvents.OnAddNoteClick ->{
                sendUiEvent(event = UiEvent.Navigate(Routes.ADD_EDIT_NOTE.name))
            }

            is NotesEvents.onChangeNoteFilter ->{
              searchByTitle.value = event.filter
                    _listFiltered.update {
                        _notes.value.filter { note ->
                            note?.title?.uppercase()?.contains(event.filter.uppercase())!!
                        }
                    }

              }
            is NotesEvents.onChangeLayout -> {
                changeLayout.value = !changeLayout.value
            }
            }
        }




private fun sendUiEvent(event: UiEvent){
    viewModelScope.launch{
        _uiEvent.send(event)
    }
}

    fun priorityName(priority: String):Color{
        when (priority){
            Note.Priority.LOW.name->{
                return Color(0xffB93160)
            }
            Note.Priority.MEDIUM.name->{
                return Color(0xffEAE509)
            }
            Note.Priority.HIGH.name->{
                return Color(0xff2B7A0B)
            }
            else -> {
                return Color(0xff100F0F)
            }

        }
    }

}
