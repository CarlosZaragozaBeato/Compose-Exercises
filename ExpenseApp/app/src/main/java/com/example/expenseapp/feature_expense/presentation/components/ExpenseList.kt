package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense

@Composable
fun ExpenseList(viewModelExpense: viewModelExpense = hiltViewModel()){


    val list = viewModelExpense.listOfExpenses.collectAsState()
    Surface(
        modifier = Modifier
        .fillMaxHeight(.75f),

    ) {
        LazyColumn() {
            items(list.value?.size!!) { index ->
                ExpenseCard(list.value!![index])
            }
        }
    }
}