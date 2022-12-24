package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.body_notes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.HomeViewModel
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.HomeEvents

@Composable
fun StaggeredList(
    list: List<note_dbItem>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item() {
            StaggeredVerticalGrid(
                maxColumnWidth = 220.dp,
            ) {
                list.forEach() { note ->
                    NoteItem(note,
                        onDeleteNote ={viewModel.onEvent(HomeEvents.OnDeleteNote(it))},
                        onNavigate = {viewModel.onEvent(HomeEvents.OnNavigate("${Routes.ADD_EDIT.name}?noteId=${it}"))})
                }
            }
        }
    }
}