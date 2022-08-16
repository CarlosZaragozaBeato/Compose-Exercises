package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home.HomeViewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.theme.LocalSpacing
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components.DeletePomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components.NormalPomodoro
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.HomeScreenEvents
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.utils.PomodoroState

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetCustom(
    viewModel: HomeViewModel = hiltViewModel(),

    ){
    val pomodoros = viewModel.listPomodoros.collectAsState()
    if(pomodoros.value.isEmpty()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .padding(vertical = LocalSpacing.current.small),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(imageVector = Icons.Default.Menu, contentDescription ="Menu" )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .clickable {  viewModel.onEvent(HomeScreenEvents.OnAddPomodoro)}
            ) {
                Text("No Pomodoros...")

                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon")


            }
        }

    }else{ Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f)
            .padding(vertical = LocalSpacing.current.small),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = Icons.Default.Menu, contentDescription ="Menu" )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text("Pomodoros",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Row {
                IconButton(onClick = {
                    if(viewModel.currentPomodoroState.value == PomodoroState.NORMAL){
                        viewModel.onEvent(HomeScreenEvents.OnChangePomodoroState(PomodoroState.DELETE))
                    }else{
                        viewModel.onEvent(HomeScreenEvents.OnChangePomodoroState(PomodoroState.NORMAL))
                    }

                }) {
                    Icon(imageVector = Icons.Default.Dashboard,
                        contentDescription = "Block Icon")
                }
                    IconButton(onClick = { viewModel.onEvent(HomeScreenEvents.OnAddPomodoro) }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "Add Icon")
                }
            }
        }



           LazyVerticalGrid(
               cells = GridCells.Fixed(3),
               contentPadding = PaddingValues(8.dp)
           ) {
               items(pomodoros.value) { item ->
                   when(viewModel.currentPomodoroState.value){
                       PomodoroState.NORMAL -> {
                           NormalPomodoro(
                               item = item
                           ) {
                               viewModel.onEvent(HomeScreenEvents.OnSelectPomodoro(it))
                           }
                       }
                       PomodoroState.DELETE -> {
                           DeletePomodoro(
                               item = item
                           ){
                               viewModel.onEvent(HomeScreenEvents.DeletePomodoro(it))
                           }                    }
                   }
               }
           }
       }
    }
}