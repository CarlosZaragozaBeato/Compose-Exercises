package com.example.expenseapp.feature_expense.presentation.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import java.util.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.util.Formater
import java.time.LocalDateTime


@Composable
fun DatePicker(
    viewModelExpense: viewModelExpense = hiltViewModel()
){

    val context = LocalContext.current

    val year:Int
    val month:Int
    val day:Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        {_:DatePicker,year: Int, month: Int, dayOfMonth: Int ->
            val date = Date(year, month, dayOfMonth)
            viewModelExpense.date.value = date
        }, year, month, day
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text( text = if(viewModelExpense.date.value == null) "" else "Selected Date: ${Formater(viewModelExpense.date.value!!)}",
                style = TextStyle(
                    color = Colors.white,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                )
        )

        ButtonTransaction(text = "Choose Date"){
            datePickerDialog.show()
        }

    }


}