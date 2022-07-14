package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.expenseapp.feature_expense.core.Colors

@Composable
fun ButtonTransaction(
    text:String = "Example",
    onAction: ()->Unit = {}
){
    Button(onClick = { onAction() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.brown
        )
    ) {
        Text(text = text,
            style = TextStyle(
                color = Colors.white,
                fontWeight = FontWeight.Bold
            )
        )
    }
}