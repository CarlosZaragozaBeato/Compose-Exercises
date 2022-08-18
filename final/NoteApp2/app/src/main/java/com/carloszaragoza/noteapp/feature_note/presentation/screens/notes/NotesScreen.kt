package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components.LazyItems
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components.RowItems
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components.TopBarCustom
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents
import com.carlos_zaragoza.noteapp.feature_note.presentation.utils.UiEvent

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun NotesScreen(
    onNavigate: (UiEvent.Navigate)-> Unit ,
    viewModel: NotesViewModel = hiltViewModel(),

) {

    val notes = viewModel.notes.collectAsState()
    val listFiltered = viewModel.listFiltered.collectAsState()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarCustom(
                value = viewModel.searchByTitle.value,
                onFilterChange = {
                    viewModel.onEvent(NotesEvents.onChangeNoteFilter(it))
                },
                changeLayout = viewModel.changeLayout.value,
                onChangeLayout = {
                    viewModel.onEvent(NotesEvents.onChangeLayout)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(backgroundColor = MaterialTheme.colors.primary,
                onClick = { viewModel.onEvent(NotesEvents.OnAddNoteClick) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    tint = MaterialTheme.colors.background
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background
    ) {


        if (listFiltered.value.isEmpty()) {
            if (!viewModel.changeLayout.value) {
                LazyItems(
                    viewModel = viewModel,
                    list = notes.value
                )
            } else {
                RowItems(
                    viewModel = viewModel,
                    list = notes.value
                )
            }
        } else {
            if (!viewModel.changeLayout.value) {
                LazyItems(
                    viewModel = viewModel,
                    list = listFiltered.value
                )
            } else {

                RowItems(
                    viewModel = viewModel,
                    list = listFiltered.value
                )

            }
        }
    }
}



