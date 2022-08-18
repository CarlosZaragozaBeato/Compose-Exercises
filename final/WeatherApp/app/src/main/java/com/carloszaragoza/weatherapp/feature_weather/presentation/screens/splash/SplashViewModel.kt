package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash.utils.SplashEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel: ViewModel() {


    val width = mutableStateOf(Animatable(0f))
    val scale = mutableStateOf(Animatable(0f))


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SplashEvents){
        when(event){
            is SplashEvents.OnNavigate -> {
                sendUiEvent(UiEvent.onNavigate(Routes.COUNTRY.name))
            }
            is SplashEvents.OnPop -> {
                sendUiEvent(UiEvent.popBackStack)
            }
        }
    }
    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}