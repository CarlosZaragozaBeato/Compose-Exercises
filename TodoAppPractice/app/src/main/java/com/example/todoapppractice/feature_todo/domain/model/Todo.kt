package com.example.todoapppractice.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_tbl")
data class Todo (
    val title:String,
    val description:String?,
    val isDone:Boolean,
    @PrimaryKey val id: Int? =null
)

