package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.triviaapp.feature_trivia.presentation.navigation.Routes
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash.utils.SplashEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashViewModel:ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SplashEvents){
        when(event){
            SplashEvents.OnHomePage ->{
                sendUiEvent(event = UiEvent.Navigate(Routes.HOME.name))
            }
            SplashEvents.OnPopBackStack ->{
                sendUiEvent(event = UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}