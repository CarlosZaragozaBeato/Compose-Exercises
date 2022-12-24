package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.body_notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.HomeViewModel
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.HomeEvents

@Composable

fun LazyColumnCustom(
    list : List<note_dbItem>,
    viewModel: HomeViewModel = hiltViewModel()
){
    LazyColumn(modifier = Modifier
        .fillMaxSize()){
        items(list){ item ->
            NoteItem(item,
            onDeleteNote ={viewModel.onEvent(HomeEvents.OnDeleteNote(it))},
            onNavigate = {viewModel.onEvent(HomeEvents.OnNavigate(Routes.ADD_EDIT.name+"?noteId=${it}"))})
        }
    }
}