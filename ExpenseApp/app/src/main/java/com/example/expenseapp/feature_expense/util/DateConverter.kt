package com.example.expenseapp.feature_expense.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun TimeStampFromDate(date: Date):Long{
        return date.time
    }
    @TypeConverter
    fun dateFromTimeStamp(timeStamp: Long): Date {
        return Date(timeStamp)
    }
}