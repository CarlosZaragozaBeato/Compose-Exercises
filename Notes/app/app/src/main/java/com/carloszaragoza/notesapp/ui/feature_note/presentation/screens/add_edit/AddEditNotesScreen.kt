package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.body_add.DropMenuCustom
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.body_add.TextFieldCustomBody
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.bottom_sheet.BottomSheetCustom
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.components.add_edit_app_bar.AddEditAppBar
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util.AddEditEvents
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing
import com.carloszaragoza.notesapp.ui.feature_note.presentation.util.UiEvent
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNotesViewModel = hiltViewModel(),
    onPop:()->Unit,
    onNavigate:(UiEvent.Navigate) -> Unit
){

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is UiEvent.OnPop-> onPop()
                is UiEvent.ShowSnackBar-> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message)
                }
                is UiEvent.Navigate -> onNavigate(event)
            }
        }
    }
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(state.color.value),

        sheetContent = {
            BottomSheetCustom(
                viewModel = viewModel,
                sheetState = sheetState
            )
        },
        sheetElevation = 0.dp,
        sheetPeekHeight = 0.dp,
        topBar = {
            AddEditAppBar(
                onPop = {viewModel.onEvent(AddEditEvents.OnPop)},
                onSave = {viewModel.onEvent(AddEditEvents.OnSaveNote)})
        }
    ){
     if(!state.isLoading.value){
         Column(
             modifier = Modifier
                 .padding(
                     horizontal = LocalSpacing.current.small,
                     vertical = LocalSpacing.current.medium
                 )
                 .fillMaxHeight(),
             verticalArrangement = Arrangement.SpaceBetween
         ){
             Column {
                 TextFieldCustomBody(
                     placeholder = "Enter a Title...",
                     textStyle = TextStyle(
                         color = Color.White,
                         fontWeight = FontWeight.Bold,
                         fontSize = 25.sp
                     ),
                     text = viewModel.title.value
                 ){ title ->
                     viewModel.onEvent(AddEditEvents.OnTitleChange(title))
                 }

                 TextFieldCustomBody(
                     placeholder = "Enter a Description...",
                     textStyle = TextStyle(
                         color = Color.White.copy(.9f),
                         fontWeight = FontWeight.SemiBold,
                         fontSize = 20.sp
                     ),
                     maxLines = 5,
                     text = viewModel.description.value
                 ){ description ->
                     viewModel.onEvent(AddEditEvents.OnDescriptionChange(description))
                 }

                 DropMenuCustom(
                     currentValue = state.priority.value,
                     onChangePriority = {priority ->
                         viewModel.onEvent(AddEditEvents.OnPriorityChange(priority))
                     },
                     expanded = state.expanded,
                     itemsList = note_dbItem.listPriority
                 )
             }

             IconButton(onClick = {
                 scope.launch {
                     sheetState.expand()
                 }
             }) {
                 Icon(imageVector = Icons.Default.Palette,
                     contentDescription = "Colors",
                     tint = Color.White)
             }
         }
     }else{
       Box(
           modifier = Modifier.fillMaxSize(),
           contentAlignment = Alignment.Center
       ){
           CircularProgressIndicator()
       }
     }

    }

}