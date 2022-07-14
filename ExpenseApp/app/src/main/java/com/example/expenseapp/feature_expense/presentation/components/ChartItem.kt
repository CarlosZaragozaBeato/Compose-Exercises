package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.domain.model.DayTransaction
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChartItem(
    dayOfTheWeek: DayTransaction,
    viewModelExpense: viewModelExpense = hiltViewModel()){




    Column(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModelExpense.changeDay(dayOfTheWeek.day),
            style = TextStyle(fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Colors.white,
                ),
            modifier = Modifier
                .padding(bottom= 10.dp)
        )
            Box(    modifier = Modifier
                .fillMaxHeight()
                .background(Colors.white)
                .width(10.dp),
                contentAlignment = Alignment.BottomCenter){
                Spacer(modifier = Modifier
                    .fillMaxHeight(dayOfTheWeek.bar!!)
                    .width(10.dp)
                    .background(Colors.lightBrow)
                    )
            }
    }





}
