package com.carlos_zaragoza.noteapp.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carlos_zaragoza.noteapp.feature_note.presentation.theme.listOfColor



@Entity(tableName = "note_tbl")
data class Note(
    val title:String,
    val description:String?,
    val priority:String,
    val color: Int,
    @PrimaryKey val id:Int? = null
){
    enum class Priority{
        LOW,
        MEDIUM,
        HIGH
    }
    companion object {
        val noteColors = listOfColor


    }

}
