package com.example.todoapppractice.feature_todo.presentation.screens.todo_list.utils

import com.example.todoapppractice.feature_todo.domain.model.Todo

sealed class TodoListEvents{

    data class onDeleteTodoClick(val todo: Todo):TodoListEvents()
    data class OnDoneChange(val todo:Todo, val isDone:Boolean):TodoListEvents()
    object OnUndoDeleteClick:TodoListEvents()
    data class OnTodoClick(val todo:Todo):TodoListEvents()
    object OnAddTodoClick:TodoListEvents()

}
