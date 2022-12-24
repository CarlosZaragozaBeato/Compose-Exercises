package com.carloszaragoza.groceryapp.feature_login.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_login.presentation.login.util.LoginEvents
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: UserUseCases
): ViewModel() {

    private val _userText = mutableStateOf("")
    val userText: State<String> = _userText

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _conditionPassword = mutableStateOf(true)
    val conditionPassword:State<Boolean> = _conditionPassword

    private val _conditionUser = mutableStateOf(true)
    val conditionUser:State<Boolean> = _conditionUser

    fun onEvent(event: LoginEvents){
        when(event) {
            is LoginEvents.OnLogin -> {
                checkUser()
            }
            is LoginEvents.OnPasswordChange -> {
                _userPassword.value = event.password
            }
            is LoginEvents.OnUsernameChange -> {
                _userText.value = event.username
            }
            is LoginEvents.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(event.route))
            }
        }
    }



    private fun checkUser(){
        viewModelScope.launch(Dispatchers.IO){
            if(_userText.value.isBlank() && _userPassword.value.isBlank()){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The user and password cannot be empty"))
                _conditionPassword.value = false
                _conditionUser.value = false
                return@launch
            }else if(_userPassword.value.length<8){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The password needs to be 8 characters"))
                _conditionPassword.value = false
                _conditionUser.value = true
                return@launch
            }else {
                useCase.getUser(username = _userText.value, password = _userPassword.value)
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
                        useCase.addUser(
                            User(
                                id =  user.id,
                                logIn = 1,
                                orderList = user.orderList,
                                username = user.username,
                                password = user.password))
                        }
                    }

                sendUiEvent(UiEvent.OnNavigate(Routes.HOME.name))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }

}