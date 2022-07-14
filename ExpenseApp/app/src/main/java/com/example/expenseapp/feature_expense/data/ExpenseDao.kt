package com.example.expenseapp.feature_expense.data

import androidx.room.*
import com.example.expenseapp.feature_expense.domain.model.expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("Select * from expense_tbl")
    fun getExpenses(): Flow<List<expense>>

    @Query("Select * from expense_tbl where id = :id")
    suspend fun getSelectedExpense(id:String):expense

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(expense: expense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(expense: expense)

    @Query("Delete from expense_tbl")
    suspend fun deleteAllTransaction()

    @Delete
    suspend fun deleteTransaction(expense: expense)



}