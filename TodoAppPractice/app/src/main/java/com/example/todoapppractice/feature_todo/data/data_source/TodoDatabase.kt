package com.example.todoapppractice.feature_todo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapppractice.feature_todo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase:RoomDatabase(){
    abstract val dao:TodoDao

    companion object{
        const val DATABASE_NAME ="todo_db"
    }
}