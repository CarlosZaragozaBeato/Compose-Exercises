package com.example.notesappudemy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesappudemy.screen.NoteScreen
import com.example.notesappudemy.view_model.NoteViewModel
import com.example.notesappudemy.ui.theme.NotesAppUdemyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppUdemyTheme {

                //val noteViewModel = viewModel<NoteViewModel>()
                val noteViewModel: NoteViewModel by viewModels()
                NotesApp(
                    noteViewModel = noteViewModel
                )
            }
            }
        }
    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()){

    val notesList = noteViewModel.noteList.collectAsState()

    NoteScreen(
        notes = notesList.value,
        RemoveNote = {
            noteViewModel.removeNote(it)
        },
        onAddNote = {
            noteViewModel.addNote(it)
        },
    )
}


