package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.user.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status

data class UserState(
    val user: MutableState<User?> = mutableStateOf(null),
    val status: MutableState<Status?> = mutableStateOf(null),
    val listOfNotes: MutableState<List<note_dbItem>?> = mutableStateOf(emptyList())


)
