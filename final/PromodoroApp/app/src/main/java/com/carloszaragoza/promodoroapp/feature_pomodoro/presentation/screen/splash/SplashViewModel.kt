package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.navigation.Routes
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.splash.utils.SplashEvents
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class SplashViewModel:ViewModel() {

    private val _uiEvents = Channel<UiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()


    fun onEvent(event: SplashEvents){
        when(event){
            SplashEvents.onNavigate ->{
                sendUiEvent(UiEvent.onNavigate(Routes.SPLASH.name))
            }
        }
    }

    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch{
            _uiEvents.send(event)
        }
    }
}