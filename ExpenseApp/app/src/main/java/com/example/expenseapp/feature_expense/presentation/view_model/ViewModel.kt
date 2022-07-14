package com.example.expenseapp.feature_expense.presentation.view_model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.expenseapp.feature_expense.domain.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.expenseapp.feature_expense.domain.model.DayTransaction
import com.example.expenseapp.feature_expense.domain.model.expense
import com.example.expenseapp.feature_expense.util.Formater
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import kotlin.math.exp


@HiltViewModel
class viewModelExpense @Inject constructor(
    private val repository: ExpenseRepository
):ViewModel() {

    val title = mutableStateOf<String?>("")
    val amount = mutableStateOf<String?>("")
    val date = mutableStateOf<Date?>(null)
    var isLoading = mutableStateOf(false)
    private val  _listOfExpenses = MutableStateFlow<List<expense>?>(null)
    val listOfExpenses = _listOfExpenses.asStateFlow()




     val dayTransaction= mutableStateOf(
        listOf(
            DayTransaction(1,0.0),
            DayTransaction(2,0.0),
            DayTransaction(3,0.0),
            DayTransaction(4,0.0),
            DayTransaction(5,0.0),
            DayTransaction(6,0.0),
            DayTransaction(7,0.0),
        )
    )





    init{
        getAllTransactions()
        viewModelScope.launch {
            calculateTransactions()

        }

    }


    suspend fun calculateTransactions(){
        delay(1000)
        isLoading.value = true
        dayTransaction.value.forEach{
            it.amount = 0.0
        }

        listOfExpenses.value?.forEach{ expense ->
           dayTransaction.value.forEach(){ day->
                if(expense.date?.day == day.day){
                    day.amount +=expense.amount!!
                }
           }

        }

        calculateHeights()
        isLoading.value = false
    }



     fun calculateHeights(){
        dayTransaction.value.forEach{
            getTheHeight(it)
        }
    }


    fun getTheHeight(dayTransaction: DayTransaction){

          when(dayTransaction.amount){
           0.0 ->{
                dayTransaction.bar = .0f
          } in 1.0 .. 100.0 ->{
              dayTransaction.bar = .1f
          }in 100.0 .. 200.0 ->{
              dayTransaction.bar = .25f
          }in 200.0 .. 400.0 ->{
              dayTransaction.bar = .45f
          }in 400.0 .. 600.0 ->{
              dayTransaction.bar = .65f
          }in 600.0 .. 800.0 ->{
              dayTransaction.bar = .85f
          }else ->{
              dayTransaction.bar = 1f
          }
      }
    }



    fun changeDay(number:Int):String{

       return when(number){
            1->{
                "S"
            }2->{
                "M"
            }3->{
               "T"
            }4->{
               "W"
            }5->{
               "T"
            }6->{
               "F"
            }7->{
               "S"
            }else->{
               ""
            }
        }

    }



    fun getAllTransactions() = viewModelScope.launch {
        repository.getAllTransactions().distinctUntilChanged()
            .collect { expenses->
                _listOfExpenses.value = expenses
            }
    }


    fun deleteTransaction(expense:expense) = viewModelScope.launch {
        repository.removeTransaction(expense)
        calculateTransactions()
    }





    fun addTransaction() =viewModelScope.launch {
        repository.addTransaction(expense(title = title.value,amount = amount.value?.toDouble(), date =(date.value)))
        calculateTransactions()
    }




}



