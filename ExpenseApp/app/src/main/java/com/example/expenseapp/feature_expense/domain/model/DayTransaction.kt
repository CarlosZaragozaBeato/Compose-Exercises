package com.example.expenseapp.feature_expense.domain.model

data class DayTransaction(
    val day: Int,
    var amount:Double,
    var bar: Float? =0f,
)
