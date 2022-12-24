package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeState(
    val currentUser: MutableState<User?> = mutableStateOf(null),
    val currentLayout:MutableState<Boolean> = mutableStateOf<Boolean>(true),
    val filterText:MutableState<String> = mutableStateOf(""),
    var listOfNotesFiltered:MutableState<List<note_dbItem>?> = mutableStateOf(emptyList()),
    val listOfNotes: MutableStateFlow<List<note_dbItem>?> = MutableStateFlow(emptyList())
)
