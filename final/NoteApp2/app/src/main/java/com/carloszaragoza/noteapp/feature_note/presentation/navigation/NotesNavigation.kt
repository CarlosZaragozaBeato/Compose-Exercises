package com.carlos_zaragoza.noteapp.feature_note.presentation.navigation



import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note.AddEditNoteScreen
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.NotesScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.NOTES.name){


        composable( Routes.NOTES.name){
            NotesScreen(onNavigate = {navController.navigate(it.route)})
        }

        val routeName = "${Routes.ADD_EDIT_NOTE.name}?noteId={noteId}"
        composable( route = routeName, arguments = listOf(
            navArgument(name = "noteId"){
                type = NavType.IntType
                defaultValue = -1
            }
        )){
            AddEditNoteScreen(onPopBackStack = {navController.popBackStack()})
        }

    }

}