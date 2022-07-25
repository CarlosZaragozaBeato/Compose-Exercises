package com.example.todoapppractice.feature_todo.data.data_source

import androidx.room.*
import com.example.todoapppractice.feature_todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo:Todo)

    @Query("SELECT * FROM todo_tbl WHERE id = :id")
    suspend fun getTodoById(id:Int):Todo?

    @Query("SELECT * FROM todo_tbl")
    fun getTodos(): Flow<List<Todo>>
}