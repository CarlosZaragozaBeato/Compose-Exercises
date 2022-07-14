package com.example.expenseapp.feature_expense.domain.model


import androidx.room.Entity
 import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expense_tbl")
data class expense (
    @PrimaryKey
    val id:UUID = UUID.randomUUID(),
    val title:String?,
    val amount:Double?,
    val date: Date?
)
