package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.UserUseCases
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room.UsersRoomCases
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UserUseCases,
    private  val userUseCase: UsersRoomCases
):ViewModel() {

    private val _status:MutableState<Status?> = mutableStateOf(null)
    val status: State<Status?> = _status

    private val _loggedIn = mutableStateOf<Boolean>(false)
    val loggedIn:MutableState<Boolean> = _loggedIn

    init {
        getState()
    }

    private fun getState()= viewModelScope.launch (Dispatchers.IO){
        try{
            _status.value =  useCases.getStatus.invoke()
        }catch (Error:Exception){}
        userUseCase.getUserLoggedIn.invoke().let { user->
            _loggedIn.value = user!=null
        }
    }
}