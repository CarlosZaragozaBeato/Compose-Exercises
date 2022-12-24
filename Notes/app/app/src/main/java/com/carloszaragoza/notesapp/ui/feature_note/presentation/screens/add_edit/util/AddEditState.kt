package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.theme.listOfColor
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status

data class AddEditState(
    val priority:MutableState<String> = mutableStateOf("MEDIUM"),
    val color:MutableState<Int> = mutableStateOf(listOfColor.first().toArgb()),
    val currentUser: MutableState<User?> = mutableStateOf(null),
    val note: MutableState<note_dbItem?> = mutableStateOf(null),
    val expanded:MutableState<Boolean> = mutableStateOf(false),
    val isLoading:MutableState<Boolean> = mutableStateOf(false),
    val status:MutableState<Status?> = mutableStateOf(null),
    val noteId: MutableState<Int?> = mutableStateOf(null)
)


