package com.example.notesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.feature_note.presentation.theme.*

@Entity(tableName = "note_table")
data class Note (
    val title:String,
    val content:String,
    val timestamp:Long,
    val color:Int,
    @PrimaryKey val id: Int? = null){

    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
class InvalidNoteException(message:String): Exception(message)