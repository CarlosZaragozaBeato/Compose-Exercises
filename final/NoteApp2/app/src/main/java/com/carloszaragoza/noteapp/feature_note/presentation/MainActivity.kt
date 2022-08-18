package com.carlos_zaragoza.noteapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.carlos_zaragoza.noteapp.feature_note.presentation.navigation.NotesNavigation
import com.carlos_zaragoza.noteapp.feature_note.presentation.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
            val navController = rememberNavController()
                NotesNavigation(navController = navController)
            }
        }
    }
}

