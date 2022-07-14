package com.example.expenseapp.feature_expense.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.expenseapp.feature_expense.domain.model.expense
import com.example.expenseapp.feature_expense.util.DateConverter
import com.example.expenseapp.feature_expense.util.UuidConverter


@Database(
    entities = [expense::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    UuidConverter::class
)
abstract class ExpenseDataBase:RoomDatabase() {

    abstract fun expenseDao():ExpenseDao
}