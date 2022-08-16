package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.pomodoro.feature_login.domain.use_cases.PomodoroUseCases
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.navigation.Routes
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.HomeScreenEvents
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.HomeScreenState
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.PomodoroState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases : PomodoroUseCases
):ViewModel() {

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _listPomodoros = MutableStateFlow<List<Pomodoro>>(emptyList())
    val listPomodoros = _listPomodoros.asStateFlow()

    val pomodoroSelected = mutableStateOf<Pomodoro?>(null)
    val currentTime = mutableStateOf<Double?>(0.0)
    val currentPosition = mutableStateOf<Float>(0f)



    val currentState = mutableStateOf<HomeScreenState>(HomeScreenState.INITIAL)
    val currentPomodoroState = mutableStateOf<PomodoroState>(PomodoroState.NORMAL)




    val openDialog = mutableStateOf(false)
    val utilVariable = mutableStateOf<Double?>(null);

    init{
    viewModelScope.launch {
        useCases.getPomodoros.invoke().collect{
            _listPomodoros.value = it
            }
        }
    }


    fun onEvent(event:HomeScreenEvents){
        when(event){
            is HomeScreenEvents.OnSelectPomodoro -> {
                OnResetPromodoro(event.pomodoro)
                utilVariable.value = (event.pomodoro.focusTime * 62)
            }
            is HomeScreenEvents.StartPomodoro -> {
                pomodoroStart()
            }
            is HomeScreenEvents.PausePomodoro -> {
                currentState.value = HomeScreenState.STOPPED
            }
            is HomeScreenEvents.OnChangePomodoroState -> {
                currentPomodoroState.value = event.state
            }
            is HomeScreenEvents.DeletePomodoro -> {
                onDeletePomodoro(event.pomodoro)
            }
            is HomeScreenEvents.OnAddPomodoro -> {
                sendUiEvent(UiEvent.onNavigate(Routes.ADD.name))
            }
        }
    }




    fun pomodoroStart(){
    utilVariable.value = pomodoroSelected.value?.focusTime!! * 62

        viewModelScope.launch{
            currentState.value = HomeScreenState.RUNNING
            while (currentTime.value!! >0 && currentState.value == HomeScreenState.RUNNING){


                delay(1000)
                if(currentState.value == HomeScreenState.RUNNING){
                    currentTime.value = currentTime.value!! -.017
                    currentPosition.value += (1/ utilVariable.value!!).toFloat()
                }
            }

           if(currentTime.value!! <=0.0){
                OnResetPromodoro(pomodoroSelected.value!!)
                openDialog.value = true
           }
          }
        }


    fun OnResetPromodoro(pomodoro: Pomodoro){
            currentState.value = HomeScreenState.INITIAL
            pomodoroSelected.value = pomodoro
            currentTime.value = pomodoro.focusTime
            currentPosition.value = 0f
    }


    fun onDeletePomodoro(pomodoro: Pomodoro){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deletePomodoro(pomodoro)

            useCases.getPomodoros.invoke().collect{
                _listPomodoros.value = it
            }

        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }



}