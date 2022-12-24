package com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_tbl")
 class note_dbItem(
    val COLOR: Int,
    val CONTENT: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val PRIORITY: String,
    val TITLE: String,
    val USER_ID: Int,
){
    companion object {
        val listPriority = listOf(
            "LOW",
            "MEDIUM",
            "HIGH"
        )
    }
}