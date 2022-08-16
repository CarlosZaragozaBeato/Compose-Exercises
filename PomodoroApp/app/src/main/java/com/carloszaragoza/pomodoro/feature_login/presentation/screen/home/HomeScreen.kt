package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home.components.BottomSheetCustom
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components.ButtonActionsPomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components.TimerCircle
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.HomeScreenEvents
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.HomeScreenState

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onNavigation:(UiEvent.onNavigate) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
){

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    LaunchedEffect(key1 =true){
        viewModel.uiEvent.collect{event->
            when(event){
               is UiEvent.onNavigate -> onNavigation(event)
                else -> Unit
            }
        }
    }




    BottomSheetScaffold(
        scaffoldState = scaffoldState,

        sheetContent = {
            BottomSheetCustom(
                viewModel = viewModel
            )
        },
        sheetShape = RectangleShape,
        sheetPeekHeight = 40.dp,
    topBar = {
        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent,
            title = {Text("Pomodoro App")},
           )
        }
    ){

/*
*         PomodoroDialog(
            openDialog = viewModel.openDialog.value
        )
* */

        val list = viewModel.listPomodoros.collectAsState()
                 Column(
                     horizontalAlignment = Alignment.CenterHorizontally,
                     verticalArrangement = Arrangement.SpaceAround,
                     modifier = Modifier
                         .fillMaxSize()

                 ){

                 if(viewModel.pomodoroSelected.value == null || list.value.isEmpty()){
                         Text("Select a Pomodoro...")

                 }
                 else{
                         TimerCircle(viewModel = viewModel)

                         if(viewModel.currentState.value == HomeScreenState.INITIAL ||
                             viewModel.currentState.value == HomeScreenState.STOPPED){

                             ButtonActionsPomodoro(
                                 onAction = {viewModel.onEvent(HomeScreenEvents.StartPomodoro)},
                                 text = "Start")
                         }
                         else {
                             ButtonActionsPomodoro(
                                 onAction = {viewModel.onEvent(HomeScreenEvents.PausePomodoro)},
                                 text = "Pause")
                         }

                 }
                 }
    }




}

