package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login

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
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login.util.LoginEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val userRoomUseCase: UsersRoomCases
): ViewModel() {

    private val _userText: MutableState<String> = mutableStateOf("")
    val userText: MutableState<String> = _userText

    private val _userPassword: MutableState<String> = mutableStateOf("")
    val userPassword: MutableState<String> = _userPassword

    private val _conditionUser: MutableState<Boolean> = mutableStateOf(true)
    val conditionUser: MutableState<Boolean> = _conditionUser

    private val _conditionPassword: MutableState<Boolean> = mutableStateOf(true)
    val conditionPassword: MutableState<Boolean> = _conditionPassword

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _status:MutableState<Status?> = mutableStateOf(null)
    val status: MutableState<Status?> = _status


    init{
        viewModelScope.launch{
            try{
                userUseCases.getStatus.invoke().let {
                    _status.value = it
                }
            }catch (E:Exception){
                Log.d("ERROR", E.message.toString())
            }
        }
    }

    fun onEvent(event: LoginEvents){
        when(event){
            is LoginEvents.OnUsername -> {
                _userText.value = event.username
            }
            is LoginEvents.OnPassword -> {
                _userPassword.value = event.password
            }
            is LoginEvents.OnLogin -> {
                checkUser()
            }
            is LoginEvents.OnNavigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
        }
    }

    private fun checkUser(){
        viewModelScope.launch(Dispatchers.IO){

           try{
               userUseCases.getStatus.invoke().let {
                   _status.value = it
               }
           }catch (e:Exception){
               Log.d("Me", e.message.toString())
           }
            if(_status.value!=null){
            if(_userText.value.isBlank() && _userPassword.value.isBlank()){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The user and password cannot be empty"))
                _conditionPassword.value = false
                _conditionUser.value = false
                return@launch
            }else if(_userPassword.value.length<6){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The password needs to be 6 characters"))
                _conditionPassword.value = false
                _conditionUser.value = true
                return@launch
            }else {
                userUseCases.getUser(name = _userText.value, password = _userPassword.value)
                    .let { user ->
                        if (user == null) {
                            sendUiEvent(
                                UiEvent.ShowSnackBar(
                                    message = "The user or the password are incorrect"
                                )
                            )
                            _conditionPassword.value = false
                            _conditionUser.value = false
                        } else {
                            _conditionPassword.value = true
                            _conditionUser.value = true


                            val listOfItems: MutableList<note_dbItem> = mutableListOf()

                            userUseCases.getNotes(
                                name = user.data!!.first().name,
                                password = user.data!!.first().password
                            )?.forEach {
                                listOfItems.add(it)
                            }

                            userRoomUseCase.addUser(
                                User(
                                    ID = user.data?.first()?.ID,
                                    noteList = noteList(listOfItems),
                                    name = user.data?.first()?.name.toString(),
                                    logIn = 1,
                                    password = user.data?.first()?.password.toString()))
                            }
                        sendUiEvent(UiEvent.Navigate(Routes.HOME.name + "?status=500"))
                        }
                    }
                    }else{
                        sendUiEvent(UiEvent.ShowSnackBar(message = "The server is under maintenance"))
                }
            }
    }

    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch{
            _uiEvent.send(event)
    }
}