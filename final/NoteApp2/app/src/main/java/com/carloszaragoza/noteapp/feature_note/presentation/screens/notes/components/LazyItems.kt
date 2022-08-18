package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.NotesViewModel
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LazyItems(
    viewModel: NotesViewModel = hiltViewModel(),
    list : List<Note?>
){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(list) { note ->
            NoteItem(
                note!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.onEvent(NotesEvents.OnNoteClick(note))
                    },
                onEvent = viewModel::onEvent,
                background = viewModel.priorityName(note.priority)
            )
        }
    }
}