package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note.util.AddEditNoteEvent
import com.carlos_zaragoza.noteapp.feature_note.presentation.utils.UiEvent
import com.carloszaragoza.noteapp.feature_note.presentation.screens.notes.components.CustomTextField


@Composable
fun AddEditNoteScreen(
    onPopBackStack: ()-> Unit,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val icon = if (viewModel.expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { onPopBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack ,
                            contentDescription = "Arrow Back Icon",
                            tint = MaterialTheme.colors.primaryVariant)
                    }
                },
                title = {
                    Text(text = "Note", style =
                        TextStyle(fontSize = 20.sp,
                            color = Color(0xffEEEEEE)))
                },
                actions = {
                    Row(){
                      if(viewModel.noteId != -1){
                          IconButton(onClick = { viewModel.onEvent(AddEditNoteEvent.OnRemoveNote)
                          onPopBackStack.invoke()}) {
                              Icon(
                                  imageVector = Icons.Default.Delete,
                                  contentDescription = "Remove Note",
                                  tint = MaterialTheme.colors.primaryVariant
                              )
                          }
                      }
                        IconButton(onClick = { viewModel.onEvent(AddEditNoteEvent.OnSaveNoteClick) }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Add Note",
                                tint = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }
                }
            )
        },
        backgroundColor = if(viewModel.color.value == Color(0xffEEEEEE) ) MaterialTheme.colors.background
                            else viewModel.color.value,


    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .height(250.dp)
                    .padding(top = 15.dp)
            ) {
                CustomTextField(
                    text =  viewModel.title.value,
                    onValueChange = {viewModel.onEvent(AddEditNoteEvent.OnTitleChange(it))},
                    placeholder = "Title...")

                CustomTextField(
                    text =  viewModel.description.value,
                    onValueChange = {viewModel.onEvent(AddEditNoteEvent.OnDescriptionChange(it))},
                    placeholder = "Description...")

                Column(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom,

                ) {
                    OutlinedTextField(
                        value = viewModel.priority.value,
                        onValueChange ={},
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                viewModel.textfieldSize = coordinates.size.toSize()
                            },
                        enabled = false,
                        trailingIcon = {
                            Icon(icon, "contentDescription",
                                Modifier.clickable { viewModel.expanded = !viewModel.expanded },
                                tint = MaterialTheme.colors.secondary)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            disabledTextColor = MaterialTheme.colors.secondary,
                            disabledIndicatorColor = MaterialTheme.colors.secondary,
                            disabledLabelColor =   MaterialTheme.colors.secondary,


                        ),
                    )
                    DropdownMenu(

                        expanded = viewModel.expanded,
                        onDismissRequest = { viewModel.expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { viewModel.textfieldSize.width.toDp() })
                    ) {
                        viewModel.suggestions.forEach { label ->
                            DropdownMenuItem(onClick = {
                                viewModel.onEvent(AddEditNoteEvent.OnPriorityChange(label.name))
                                viewModel.expanded = false
                            }) {
                                Text(text = label.name)
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Note.noteColors.forEach{color ->
                   Column(
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Bottom,
                   ){
                       Card(modifier = Modifier
                           .size(60.dp)
                           .clickable {
                               viewModel.onEvent(AddEditNoteEvent.OnColorChange(Color(color.value.value)))
                           },
                           elevation = 7.dp,
                           border = BorderStroke(width = 1.dp, color = Color(0xff748DA6)),
                           shape = CircleShape,
                           backgroundColor = color.value){
                       }
                       Spacer(modifier = Modifier.height(10.dp))
                       
                       Text(color.key,
                        style = TextStyle(
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold
                        ))
                   }
                }
            }
        }




    }
}

