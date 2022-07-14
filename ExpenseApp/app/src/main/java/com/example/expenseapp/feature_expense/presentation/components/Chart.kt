package com.example.expenseapp.feature_expense.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import java.util.*

@SuppressLint("StateFlowValueCalledInComposition")
@Preview
@Composable
fun Chart( viewModel: viewModelExpense = hiltViewModel()){

    val listTransaction = viewModel.dayTransaction



    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.2f)
            .padding(5.dp),
        color = Colors.brown
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (viewModel.isLoading.value) {
            } else {
                listTransaction.value.forEach {
                    ChartItem(it)
                }

        }
    }

    }


}