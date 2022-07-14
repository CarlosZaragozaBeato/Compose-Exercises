package com.example.expenseapp.feature_expense.util

import java.text.SimpleDateFormat
import java.util.*


fun Formater(date:Date):String{

    val dateFormated = "${date.year}/${date.month}/${date.day}"
    return dateFormated
    }

