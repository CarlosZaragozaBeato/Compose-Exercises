package com.example.notesappudemy.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappudemy.data.NoteData
import com.example.notesappudemy.model.Note
import com.example.notesappudemy.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    //private var noteList = mutableStateListOf<Note>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
                repository.getAllNotes().distinctUntilChanged()
                    .collect{ listOfNotes->

                            _noteList.value = listOfNotes

                    }
        }
    }

     fun addNote(note:Note) = viewModelScope.launch {
        repository.addNote(note)
    }


     fun removeNote(note:Note) = viewModelScope.launch {
        repository.removeNote(note)
    }

     fun updateNote(note:Note) = viewModelScope.launch {
        repository.updateNote(note)
    }



    fun getAllNotes(): List<Note>{
        return noteList.value
    }

}