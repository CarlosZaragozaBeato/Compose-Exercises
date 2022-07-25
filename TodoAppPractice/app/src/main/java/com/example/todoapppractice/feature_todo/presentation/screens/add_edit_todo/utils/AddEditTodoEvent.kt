package com.example.todoapppractice.feature_todo.presentation.screens.add_edit_todo.utils

sealed class AddEditTodoEvent{
    data class OnTitleChange(val title:String):AddEditTodoEvent()
    data class OnDescriptionChange(val description: String):AddEditTodoEvent()
    object OnSaveTodoClick:AddEditTodoEvent()
}