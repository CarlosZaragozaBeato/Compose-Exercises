package com.example.expenseapp.feature_expense.util

import androidx.room.TypeConverter
import java.util.*

class UuidConverter {

    @TypeConverter
    fun fromUUID(uuid: UUID):String{
        return uuid.toString()
    }

    @TypeConverter
    fun UUIDFromString(string:String): UUID?{
        return UUID.fromString(string)
    }
}