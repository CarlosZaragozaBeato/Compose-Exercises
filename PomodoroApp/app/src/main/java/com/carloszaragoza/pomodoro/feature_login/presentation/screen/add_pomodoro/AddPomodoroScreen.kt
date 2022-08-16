package com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components.InputColors
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components.InputsPomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components.utils.AddPomodoroEvents


@Composable
fun AddPomodoro(
    viewModel: AddViewModel = hiltViewModel(),
    onPop: () -> Unit
){

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                 is UiEvent.popBackStack -> onPop()
                else -> Unit
            }
        }
    }



    Scaffold(
        backgroundColor = viewModel.currentColor.value,
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                title = {
                    Text("A D D")
                },
                actions = {
                    IconButton(onClick = {viewModel.onEvent(AddPomodoroEvents.PopBack) }) {
                        Icon(imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon")
                    }
                }) }
    ) {

       Column(
           modifier = Modifier
               .padding(vertical = 30.dp, horizontal = 20.dp)
               .fillMaxHeight(),
           verticalArrangement = Arrangement.SpaceBetween,

       ) {
        InputsPomodoro(text = viewModel.textInput,
                        value = viewModel.selection ,
                        range = viewModel.range,){
            if(viewModel.textInput.value.isNotEmpty()){
                viewModel.addPomodoro(Pomodoro(
                    name = viewModel.textInput.value,
                    focusTime = viewModel.selection.value.toDouble(),
                    color = viewModel.currentColor.value.toArgb()
                ))
                viewModel.onEvent(AddPomodoroEvents.PopBack)
            }

        }
        InputColors(color = viewModel.currentColor,
                    )
       }



    }



}