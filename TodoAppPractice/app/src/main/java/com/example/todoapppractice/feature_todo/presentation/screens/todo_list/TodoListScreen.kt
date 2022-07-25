package com.example.todoapppractice.feature_todo.presentation.screens.todo_list

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.components.TodoItem
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.utils.TodoListEvents
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.view_model.TodoListViewModel
import com.example.todoapppractice.feature_todo.presentation.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun TodoListScreen(
    onNavigate:(UiEvent.Navigate) -> Unit,
    viewModel:TodoListViewModel = hiltViewModel()){
    
    val todos = viewModel.todos.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if(result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(TodoListEvents.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
                 TopAppBar(
                     title = {
                         Text("Todo App")
                     }
                 )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TodoListEvents.OnAddTodoClick) }) {

                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon")

            }
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(todos.value){ todo ->
                TodoItem(
                    todo = todo,
                    onEvent = viewModel::onEvent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{
                            viewModel.onEvent(TodoListEvents.OnTodoClick(todo))
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}