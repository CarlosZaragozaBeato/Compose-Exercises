package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.noteList
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.UserRoomRepository
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.UserUseCases
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register.util.RegisterEvents
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
class RegisterViewModel @Inject constructor(
    private val userRoomRepository: UserRoomRepository,
    private val useCase: UserUseCases
):ViewModel() {

    private val _username: MutableState<String> = mutableStateOf("")
    val username: MutableState<String> = _username

    private val _userPassword: MutableState<String> = mutableStateOf("")
    val userPassword: MutableState<String> = _userPassword

    private val _confirmUserPassword: MutableState<String> = mutableStateOf("")
    val confirmUserPassword: MutableState<String> = _confirmUserPassword

    private val _conditionUser: MutableState<Boolean> = mutableStateOf(true)
    val conditionUser: MutableState<Boolean> = _conditionUser

    private val _conditionPassword: MutableState<Boolean> = mutableStateOf(true)
    val conditionPassword: MutableState<Boolean> = _conditionPassword

    private val _conditionConfirmPassword: MutableState<Boolean> = mutableStateOf(true)
    val conditionConfirmPassword: MutableState<Boolean> = _conditionConfirmPassword

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _status:MutableState<Status?> = mutableStateOf(null)
    val status: MutableState<Status?> = _status

    init{
        viewModelScope.launch{
            try{
                useCase.getStatus.invoke().let {
                    _status.value = it
                }
            }catch (E:Exception){
                Log.d("ERROR", E.message.toString())
            }

        }
    }

    fun onEvent(event: RegisterEvents){
        when(event) {
            is RegisterEvents.OnPasswordChanged -> {
                _userPassword.value = event.password
            }
            is RegisterEvents.OnPasswordConfirmedChanged -> {
                _confirmUserPassword.value = event.confirmPassword
            }
            is RegisterEvents.OnUsernameChanged -> {
                _username.value = event.username
            }
            is RegisterEvents.OnSubmit -> {
                checkUser()
            }
            is RegisterEvents.OnNavigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
        }
    }

    private fun checkUser(){
        viewModelScope.launch(Dispatchers.IO){

            try{
                useCase.getStatus.invoke().let {
                    status.value = it
                }
            }catch (e:Exception){
                Log.d("ERROR", e.message.toString())
            }

            if(status.value != null){
                if(_username.value.isBlank() &&
                    _userPassword.value.isBlank() &&
                    _confirmUserPassword.value.isBlank()
                ){
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "The user and password cannot be empty"))
                    _conditionPassword.value = false
                    _conditionConfirmPassword.value = false
                    _conditionUser.value = false
                    return@launch
                }
                if(_userPassword.value.length<6){
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "The password needs to be 8 characters."))
                    _conditionPassword.value = false
                    _conditionConfirmPassword.value = false
                    _conditionUser.value = true
                    return@launch
                }
                if(_userPassword.value != _confirmUserPassword.value){
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Please enter the same password."))
                    _conditionPassword.value = false
                    _conditionConfirmPassword.value = false
                    _conditionUser.value = true
                    return@launch
                }
                useCase.getUser(
                    name = _username.value,
                    password = _userPassword.value
                ).let { user->
                    if(user.data?.isEmpty()!!){
                        useCase.postUser(
                            User(name = _username.value,
                                password = _userPassword.value,))

                        userRoomRepository.insertUser(User(
                            name = _username.value,
                            logIn = 1,
                            password = _userPassword.value,
                            noteList = noteList(mutableListOf())))
                        sendUiEvent(UiEvent.Navigate(Routes.HOME.name))
                    }else{
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "This user has already an account!"))
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