package com.example.notesapp.feature_note.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notesapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.notesapp.feature_note.presentation.notes.NotesScreen
import com.example.notesapp.feature_note.presentation.util.Screen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NotesNavigation(navController:NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(route = Screen.NotesScreen.route) {
            NotesScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditNoteScreen.route +
                    "?noteId={noteId}&noteColor={noteColor}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            val color = it.arguments?.getInt("noteColor") ?: -1
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
    }
}
