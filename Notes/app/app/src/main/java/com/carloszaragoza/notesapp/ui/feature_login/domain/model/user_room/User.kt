package com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.noteList

@Entity(tableName = "tbl_users")
data class User(
    @PrimaryKey
    val ID: Int?= null,
    val name: String,
    val password: String,
    val logIn:Int?=null,
    val noteList: noteList? = null
)