package com.example.notesappudemy.util

import androidx.room.TypeConverter
import java.util.*

class idConverter {

    @TypeConverter
    fun fromUUID(uuid:UUID):String{
        return uuid.toString()
    }

    @TypeConverter
    fun UUIDFromString(string:String):UUID?{
        return UUID.fromString(string)
    }
}