package com.carloszaragoza.groceryapp.feature_shop.presentation.user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.util.HomeEvents
import com.carloszaragoza.groceryapp.feature_shop.presentation.user.util.UserEvents
import com.carloszaragoza.groceryapp.feature_shop.presentation.user.util.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases
): ViewModel() {

    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        viewModelScope.launch (Dispatchers.Main){
            state.value.isLoading.value = true
            getUser()
            state.value.isLoading.value = false
        }
    }

    fun onEvent(event: UserEvents){
        when(event) {
            is UserEvents.OnPop -> {
                sendUiEvent(UiEvent.OnPop)
            }
            is UserEvents.OnLogOut -> {
                logOut()
            }
            is UserEvents.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(Routes.ITEMS_ORDER.name+"/${event.index}"))
            }
        }
    }


    private fun logOut() = viewModelScope.launch(Dispatchers.IO){
        userUseCases.addUser(
            User(
                id = state.value.user?.id!!,
                username = state.value.user?.username!!,
                password = state.value.user?.password!!,
                orderList = state.value.user?.orderList!!,
                logIn = 0))
        sendUiEvent(UiEvent.OnNavigate(Routes.LOGIN.name))
    }
    private fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        userUseCases.getUserLoggedIn().let{ user->
            _state.value.user = user
        }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}