package com.example.splitter.feature_calculator.presentation.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ViewModel:ViewModel() {

    var bill: MutableState<String> = mutableStateOf("")
    var discountSelected: MutableState<String> = mutableStateOf("")
    var data = mutableStateOf<List<String>>(listOf("5%", "10%", "15%", "25%", "50%"))
    var numberOfPeople = mutableStateOf("")
    var tipAmountPerPerson = mutableStateOf("0.0")
    var totalPerPerson = mutableStateOf("0.0")


    fun calculate(){

        if(bill.value.isNotEmpty() && numberOfPeople.value.isNotEmpty() && discountSelected.value.isNotEmpty()){

            val percentage = (discountSelected.value.split("%")[0].toDouble())/100
            val totalPP = totalPerPerson(bill.value.toDouble(), numberOfPeople.value.toInt())
            val result = (bill.value.toDouble() * percentage)
            totalPerPerson.value = totalPP.toString()
            tipAmountPerPerson.value = "%.2f".format((result))

        }

    }

    fun totalPerPerson(bill:Double, person:Int):Double{
        val result = "%.2f".format((bill/person))
        return result.toDouble()


    }

    fun reset(){

            bill.value = ""
            discountSelected.value = ""
            numberOfPeople.value = ""
            tipAmountPerPerson.value = "0.0"
            totalPerPerson.value = "0.0"

        }




}