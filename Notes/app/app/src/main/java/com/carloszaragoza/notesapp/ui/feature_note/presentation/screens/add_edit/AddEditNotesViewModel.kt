package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.UserUseCases
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room.UsersRoomCases
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util.AddEditEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util.AddEditState
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNotesViewModel @Inject constructor(
    private val userRoomUseCase: UsersRoomCases,
    private val userUseCases: UserUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _title: MutableState<String> = mutableStateOf("")
    val title:MutableState<String> = _title

    private val _description: MutableState<String> = mutableStateOf("")
    val description:MutableState<String> = _description

    val state:MutableState<AddEditState> = mutableStateOf(AddEditState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        state.value.noteId.value =  savedStateHandle.get<Int>("noteId")
        state.value.isLoading.value = true
        viewModelScope.launch (Dispatchers.IO){
                    delay(200)
                    getCurrentUser()
                    getInitialStatus()
                    if ( state.value.noteId.value!=-1) {
                        delay(200)
                        getNoteById(state.value.noteId.value!!)
                    }
            }.invokeOnCompletion {
                    state.value.isLoading.value = false
            }
        }
    private  fun getInitialStatus()= viewModelScope.launch{
        userUseCases.getStatus.invoke().let{
            state.value.status.value = it
        }
    }
     private fun getNoteById(noteId:Int) = viewModelScope.launch(Dispatchers.IO){


             userUseCases.getNoteById.invoke(
                 user_id = state.value.currentUser.value!!.ID!!,
                 note_id = noteId).let { noteDb->

                 noteDb?.first().let { note ->
                                    state.value.color.value = note?.COLOR!!
                                     state.value.priority.value = note.PRIORITY
                                    _title.value = note.TITLE
                                    note.CONTENT?.let {
                                         _description.value = it
                                     }
                                 }


         }
    }
    private fun getCurrentUser() = viewModelScope.launch(Dispatchers.IO){
            userRoomUseCase.getUserLoggedIn.invoke().let { user->
                state.value.currentUser.value = user
        }
}
    fun onEvent(event:AddEditEvents){
        when(event){
            is AddEditEvents.OnPop -> {
                sendEvent(UiEvent.OnPop)
            }
            is AddEditEvents.OnSaveNote -> {
                addNote()
            }
            is AddEditEvents.OnColorChange -> {
                state.value.color.value = event.color
            }
            is AddEditEvents.OnPriorityChange -> {
                state.value.priority.value = event.priority
            }
            is AddEditEvents.OnTitleChange -> {
                _title.value = event.title
            }
            is AddEditEvents.OnDescriptionChange -> {
                _description.value = event.description
            }
        }
    }

    private fun addNote(){
        if(title.value.isBlank()){
            sendEvent(UiEvent.ShowSnackBar(message = "Please, Insert a title."))
        }else{
            viewModelScope.launch (Dispatchers.IO){
                userUseCases.getStatus.invoke().let {
                    state.value.status.value = it
                }
              if(state.value.status.value?.Status != null){
                  if(state.value.noteId.value!=-1) {
                      userUseCases.updateNote.invoke(
                          note_dbItem(
                              id = state.value.noteId.value,
                              TITLE = _title.value,
                              CONTENT = _description.value,
                              PRIORITY = state.value.priority.value,
                              COLOR = state.value.color.value,
                              USER_ID = state.value.currentUser.value?.ID!!,))
                  }else {
                      userUseCases.createNote.invoke(
                          note_dbItem(
                              TITLE = _title.value,
                              CONTENT = _description.value,
                              PRIORITY = state.value.priority.value,
                              COLOR = state.value.color.value,
                              USER_ID = state.value.currentUser.value?.ID!!,))
                  }
              }
            }
                sendEvent(UiEvent.Navigate(Routes.HOME.name+"?status=500"))
        }
    }

    private fun sendEvent(event: UiEvent){
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.send(event)
        }
    }
}