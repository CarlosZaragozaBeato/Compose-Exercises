package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.noteList
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.UserUseCases
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room.UsersRoomCases
import com.carloszaragoza.notesapp.ui.feature_note.domain.repository.Repository
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.HomeEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.HomeState
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
    private val userUseCase: UserUseCases,
    private val userRoomUseCase: UsersRoomCases
):ViewModel() {




    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val state = mutableStateOf(HomeState())

    private val _status:MutableState<Status?> = mutableStateOf(null)
    val status: MutableState<Status?> = _status

    init {
        initialState()
    }

    private fun initialState(){
         getCurrentUser()
    }
    private fun getCurrentUser(){
        viewModelScope.launch(Dispatchers.IO){
        delay(200)
            userRoomUseCase.getUserLoggedIn.invoke().let { user->
                state.value.currentUser.value = user
            }
            try{
                userUseCase.getStatus.invoke().let {
                    _status.value = it
                }
            }catch (E:Exception){
                Log.d("ERROR", E.message.toString())
            }
            if(_status.value != null){
                getNotes()
            }else{
                state.value.listOfNotes.value = state.value.currentUser.value?.noteList?.itemsList!!
            }
        }
    }

    private fun getNotes(){
        viewModelScope.launch {
            userUseCase.getNotes(name = state.value.currentUser.value?.name.toString(),
                password = state.value.currentUser.value?.password.toString()).let {
                state.value.listOfNotes.value = it
                }

        }
    }

    fun onEvent(event: HomeEvents){
        when(event){
            is HomeEvents.ChangeLayout -> {
                state.value.currentLayout.value = !state.value.currentLayout.value
            }
            is HomeEvents.OnDeleteNote -> {
                onDeleteNote(event.note)
            }
            is HomeEvents.TextFieldChange -> {
                state.value.filterText.value = event.text
                filterNotes(event.text)
            }
            is HomeEvents.AddNote -> {
                viewModelScope.launch {
                    repository.insertNote(event.note)
                }
            }
            is HomeEvents.OnNavigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
                state.value.filterText.value = ""
            }
        }
    }



    private fun onDeleteNote(note:note_dbItem){
        viewModelScope.launch {

            if(_status.value != null){
                userUseCase.deleteUser(
                    noteId = note.id!!,
                    userId = note.USER_ID
                )

                getNotes()
            }else{
                state.value.listOfNotes.value = state.value.listOfNotes.value?.filter {
                    it.id != note.id
                }
                userRoomUseCase.addUser(
                    User(
                        name = state.value.currentUser.value?.name.toString(),
                        password =state.value.currentUser.value?.password.toString(),
                        noteList = noteList(state.value.listOfNotes.value as MutableList<note_dbItem>),
                        logIn = 1,
                        ID = state.value.currentUser.value?.ID!!))

                getCurrentUser()
            }

            if(state.value.listOfNotesFiltered.value?.isNotEmpty()!!)
                state.value.listOfNotesFiltered.value = state.value.listOfNotesFiltered.value!!.filter {
                    it.id != note.id
                }
        }
    }

    private fun filterNotes(text:String){
        state.value.listOfNotesFiltered.value = state.value.listOfNotes.value?.filter {
                it.TITLE.uppercase().contains(text.uppercase())
            }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}