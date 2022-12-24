package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.UserUseCases
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room.UsersRoomCases
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.util.UserEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.util.UserState
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCases,
    private val userRoomUseCase: UsersRoomCases
): ViewModel() {

    private val _state: MutableStateFlow<UserState> = MutableStateFlow(UserState())
    val state = _state.asStateFlow()


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getCurrentUser()
    }

    fun onEvent(event: UserEvents){
        when(event){
            is UserEvents.OnNavigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is UserEvents.OnLogOut -> {
                logOut()
            }
        }
    }

    private fun logOut() = viewModelScope.launch(Dispatchers.IO){
            userRoomUseCase.deleteUser(state.value.user.value!!)

            sendUiEvent(UiEvent.Navigate(Routes.LOGIN.name))
    }

    private fun getCurrentUser(){
        viewModelScope.launch(Dispatchers.IO){
            delay(200)
            userRoomUseCase.getUserLoggedIn.invoke().let { user->
                state.value.user.value = user
            }
            try{
                userUseCase.getStatus.invoke().let {
                    state.value.status.value = it
                }
            }catch (E:Exception){
                Log.d("ERROR", E.message.toString())
            }
            if(state.value.status.value != null){
                getNotes()
            }else{
                state.value.listOfNotes.value = state.value.user.value?.noteList?.itemsList!!
            }
        }
    }

    private fun getNotes(){
        viewModelScope.launch {
            userUseCase.getNotes(name = state.value.user.value?.name.toString(),
                password = state.value.user.value?.password.toString()).let {
                state.value.listOfNotes.value = it
            }
        }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}


