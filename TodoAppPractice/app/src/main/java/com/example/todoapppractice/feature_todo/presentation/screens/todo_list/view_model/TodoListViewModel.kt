package com.example.todoapppractice.feature_todo.presentation.screens.todo_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapppractice.feature_todo.domain.model.Todo
import com.example.todoapppractice.feature_todo.domain.repository.TodoRepository
import com.example.todoapppractice.feature_todo.presentation.navigation.RouteScreens
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.utils.TodoListEvents

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.todoapppractice.feature_todo.presentation.util.UiEvent

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
):ViewModel(){
    val todos = repository.getTodos()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: Todo? = null


    fun onEvent(event: TodoListEvents){
        when(event){
            is TodoListEvents.OnTodoClick ->{
                sendUiEvent(UiEvent.Navigate(RouteScreens.ADD_EDIT_TODO.name+
                                                                    "?todoId=${event.todo.id}"))
            }
            is TodoListEvents.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(RouteScreens.ADD_EDIT_TODO.name))
            }
            is TodoListEvents.OnUndoDeleteClick -> {
                deletedTodo?.let{ todo ->
                    viewModelScope.launch {
                        repository.inserTodo(todo)
                    }
                }
            }
            is TodoListEvents.onDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteTodo(event.todo)
                    sendUiEvent(UiEvent.ShowSnackBar(
                                                    action = "Undo",
                                                    message = "Todo Deleted"))
                }
            }
            is TodoListEvents.OnDoneChange -> {
                viewModelScope.launch {
                    repository.inserTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }
    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}