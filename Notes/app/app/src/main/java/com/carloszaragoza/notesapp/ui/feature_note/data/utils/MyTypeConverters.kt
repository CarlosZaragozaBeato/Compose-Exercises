package com.carloszaragoza.groceryapp.feature_main.data.utils

import androidx.room.TypeConverter
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.noteList
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromItemToJSON(itemsList:noteList): String {
        return Gson().toJson(itemsList)
    }
    @TypeConverter
    fun fromJSONToItem(json: String): noteList {
        return Gson().fromJson(json, noteList::class.java)
    }

}