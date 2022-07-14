package com.example.expenseapp.feature_expense.domain.repository

import com.example.expenseapp.feature_expense.data.ExpenseDao
import com.example.expenseapp.feature_expense.domain.model.expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ExpenseRepository @Inject constructor(
    private val dao: ExpenseDao
){
    suspend fun addTransaction(expense: expense) = dao.addExpense(expense)
    suspend fun removeTransaction(expense: expense) = dao.deleteTransaction(expense)
    suspend fun updateTransaction(expense: expense) = dao.updateTransaction(expense)
    suspend fun deleteAllTransactions() = dao.deleteAllTransaction()
    fun getAllTransactions(): Flow<List<expense>> = dao.getExpenses().flowOn(Dispatchers.IO)
        .conflate()
}