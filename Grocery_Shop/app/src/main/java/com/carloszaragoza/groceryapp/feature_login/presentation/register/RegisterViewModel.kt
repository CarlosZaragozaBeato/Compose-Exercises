package com.carloszaragoza.groceryapp.feature_login.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_main.domain.model.OrderList
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_login.presentation.register.util.RegisterEvents
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: UserUseCases
): ViewModel() {

    private val _userText = mutableStateOf("")
    val userText: State<String> = _userText

    private val _userPassword = mutableStateOf("")
    val userPassword: State<String> = _userPassword

    private val _userConfirmPassword = mutableStateOf("")
    val userConfirmPassword: State<String> = _userConfirmPassword

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _conditionPassword = mutableStateOf(true)
    val conditionPassword: State<Boolean> = _conditionPassword

    private val _conditionUser = mutableStateOf(true)
    val conditionUser: State<Boolean> = _conditionUser

    fun onEvent(event: RegisterEvents){
        when(event){
            is RegisterEvents.OnUsernameChange -> {
                _userText.value = event.username
            }
            is RegisterEvents.OnPasswordChange -> {
                _userPassword.value = event.password
            }
            is RegisterEvents.OnConfirmPasswordChange -> {
                _userConfirmPassword.value = event.password
            }
            is RegisterEvents.OnRegister ->{
                checkUser()
            }
            is RegisterEvents.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(event.route))
            }
        }
    }

    private fun checkUser(){
        viewModelScope.launch(Dispatchers.IO){
            if(_userText.value.isBlank() &&
                _userPassword.value.isBlank() &&
                _userConfirmPassword.value.isBlank()
            ){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The user and password cannot be empty"))
                _conditionPassword.value = false
                _conditionUser.value = false
                return@launch
            }
            if(_userPassword.value.length<8){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "The password needs to be 8 characters."))
                _conditionPassword.value = false
                _conditionUser.value = true
                return@launch
            }
            if(_userPassword.value != _userConfirmPassword.value){
                sendUiEvent(UiEvent.ShowSnackBar(
                    message = "Please enter the same password."))
                _conditionPassword.value = false
                _conditionUser.value = true
                return@launch
            }
            useCase.getUser(
                username = _userText.value,
                password = _userPassword.value
            ).let { user->
                if(user==null){
                    useCase.addUser(
                        User(username = _userText.value,
                            password = _userPassword.value,
                            logIn = 1,
                         orderList = OrderList(
                             mutableListOf()
                         )
                        ))
                    sendUiEvent(UiEvent.OnNavigate(Routes.HOME.name))
                }else{
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "This user has an account already."))
                }
            }
            }
        }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}