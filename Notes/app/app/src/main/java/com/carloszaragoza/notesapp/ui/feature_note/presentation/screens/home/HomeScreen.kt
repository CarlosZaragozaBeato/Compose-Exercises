package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.TextFieldAppBar
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.app_bar.IconAppBar
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.body_notes.LazyColumnCustom
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.body_notes.StaggeredList
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.HomeEvents
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigate:(UiEvent.Navigate) -> Unit
){

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    val state = viewModel.state.value
    val notes = viewModel.state.value.listOfNotes.collectAsState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = {
               viewModel.onEvent(HomeEvents.OnNavigate(Routes.ADD_EDIT.name))
            }, backgroundColor = MaterialTheme.colors.onBackground) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Note",
                    tint = MaterialTheme.colors.background)
            }
        },
        topBar = {
                TopAppBar(
                    title = {
                        TextFieldAppBar(
                            text = state.filterText.value ,
                        ){ filter ->
                            viewModel.onEvent(HomeEvents.TextFieldChange(filter))
                        }
                    },
                    actions = {
                        IconAppBar(
                            currentLayout = state.currentLayout.value
                        ){
                            viewModel.onEvent(HomeEvents.ChangeLayout)
                        }
                        IconButton(onClick = { viewModel.onEvent(HomeEvents.OnNavigate(Routes.USER.name)) }) {
                            Icon(imageVector = Icons.Default.Person,
                                contentDescription = "Go to the profile page")
                        }
                    },
                    modifier = Modifier
                        .height(100.dp),
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp)
            }
    ){
        if(notes.value!=null){
                if(state.currentLayout.value){
                        LazyColumnCustom(list = if(state.listOfNotesFiltered.value?.isEmpty()!!)
                                                        notes.value!!
                                                else
                                                     state.listOfNotesFiltered.value!!,
                            viewModel = viewModel)
                }else {
                        StaggeredList(list =    if(state.listOfNotesFiltered.value?.isEmpty()!!)
                                                    notes.value!!
                                                else
                                                    state.listOfNotesFiltered.value!!,
                            viewModel = viewModel)
                }
            }
        }

    }
