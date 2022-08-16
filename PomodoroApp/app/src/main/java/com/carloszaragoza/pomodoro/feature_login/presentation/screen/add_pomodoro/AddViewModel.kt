package com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.pomodoro.feature_login.domain.use_cases.PomodoroUseCases
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.listOfColor
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components.utils.AddPomodoroEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val useCases: PomodoroUseCases
): ViewModel() {


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val range: ClosedFloatingPointRange<Float> = 10f..60f
    var selection = mutableStateOf(50f)

    var textInput = mutableStateOf("")

    var currentColor = mutableStateOf(listOfColor.first())



    fun onEvent(event: AddPomodoroEvents){
        when(event) {
            is AddPomodoroEvents.AddPomodoro -> {
                addPomodoro(event.pomodoro)
            }
            is AddPomodoroEvents.PopBack -> {
                sendUiEvent(UiEvent.popBackStack)
            }
        }
    }



    fun addPomodoro(pomodoro: Pomodoro){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.addPomodoro(pomodoro)
        }
    }


    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}