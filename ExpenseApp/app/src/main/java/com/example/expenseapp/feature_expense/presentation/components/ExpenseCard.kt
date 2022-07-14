package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.domain.model.expense
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import com.example.expenseapp.feature_expense.util.Formater

@Composable
fun ExpenseCard(expense:expense,
viewModelExpense: viewModelExpense = hiltViewModel()){
    
    Card(
        backgroundColor = Colors.brown,
        elevation = 4.dp,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){

                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxHeight()

                                .background(Colors.white)){
                            Text(text = "$${expense.amount.toString()}",
                               style = TextStyle(
                                   fontSize = 17.sp,
                                   fontWeight = FontWeight.Bold,
                                   color = Colors.red
                               ),
                                    modifier = Modifier
                                        .padding(5.dp)
                            )


                    }

                    Spacer(modifier = Modifier
                        .width(10.dp))
                    Column(
                        modifier = Modifier
                            .padding(start =10.dp)
                    ) {
                        Text(
                            text = expense.title.toString(),
                            style = TextStyle(fontWeight = FontWeight.Bold,
                                color = Colors.white,
                                fontSize = 18.sp)
                        )
                        Text(
                            text = Formater(expense.date!!),
                            style = TextStyle(color = Colors.lightBrow,
                            fontSize = 16.sp)
                        )
                    }
                }

            }
            IconButton(onClick = { viewModelExpense.deleteTransaction(expense) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete icon",
                    tint = Colors.orange)
            }
        }
    }
}