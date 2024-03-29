package com.example.todoapppractice.feature_todo.presentation.screens.add_edit_todo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapppractice.feature_todo.presentation.screens.add_edit_todo.utils.AddEditTodoEvent
import com.example.todoapppractice.feature_todo.presentation.screens.add_edit_todo.view_model.AddEditTodoViewModel
import com.example.todoapppractice.feature_todo.presentation.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun AddEditTodoScreen(
    onPopBackStack:() ->Unit,
    viewModel:AddEditTodoViewModel = hiltViewModel()){


    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick)

                }) {
                    Icon(imageVector = Icons.Default.Check,
                        contentDescription = "Add Icon")
                }
            }
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(AddEditTodoEvent.OnTitleChange(it))
                },
                placeholder = {Text("Title")},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(it))
                },
                placeholder = {Text("Description")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

        }
    }


}