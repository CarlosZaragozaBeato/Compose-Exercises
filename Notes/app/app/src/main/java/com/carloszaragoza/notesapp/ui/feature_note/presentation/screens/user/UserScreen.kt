package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.util.UserEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onNavigate:(UiEvent.Navigate) -> Unit
) {

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    val state = viewModel.state.collectAsState()
    if(state.value.user.value != null){
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                                 IconButton(onClick =
                                 {viewModel.onEvent(UserEvents.OnNavigate(Routes.HOME.name+"?status=500")) }) {
                                     Icon(imageVector = Icons.Default.ArrowBack,
                                         contentDescription = "Go to home page")
                                 }
                },
                title = {
                    Text(state.value.user.value?.name.toString(),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground)
                },
                actions = {
                    TextButton(onClick = { viewModel.onEvent(UserEvents.OnLogOut)}) {
                        Text("Log Out",
                            color = Color.Red.copy(.5f),
                            fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(LocalSpacing.current.medium)
        ) {
              Text("Number Of Notes: ${state.value.listOfNotes.value?.size}",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground)
        }
    }
        }else{
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
                    ){
                CircularProgressIndicator()
            }
    }

}