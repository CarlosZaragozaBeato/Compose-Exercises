package com.example.todoapppractice.feature_todo.domain.repository

import com.example.todoapppractice.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository{

    suspend fun inserTodo(todo:Todo)

    suspend fun deleteTodo(todo:Todo)

    suspend fun getTodoById(int:Int):Todo?

    fun getTodos(): Flow<List<Todo>>
}