package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.access_options

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.access_options.util.AccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccessViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel(){
    val state: MutableState<AccessState> = mutableStateOf(AccessState())


    init {
        state.value.status = savedStateHandle.get<String>("status")!!
    }




}