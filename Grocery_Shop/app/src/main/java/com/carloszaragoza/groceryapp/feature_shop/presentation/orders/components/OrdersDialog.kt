package com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun OrdersDialog(
    openDialog:Boolean,
    close:() -> Unit= {},
    ConfirmAction:()->Unit = {},
    DissmisOperation: () -> Unit = {},
    title:String = "Title",
    question:String = "Question",
    confirmActionText:String = "Confirm",
    DissmisActionText:String = "Text"

){
    if (openDialog) {
        AlertDialog(
            backgroundColor = MaterialTheme.colors.onBackground,
            onDismissRequest = {close.invoke()},
            title = {
                Text(text = title,
                    style = TextStyle(
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(text = question,
                    style = TextStyle(
                        color = MaterialTheme.colors.background.copy(.9f),
                        fontWeight = FontWeight.Light
                    )
                )
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        close.invoke()
                        DissmisOperation()
                    }
                ) {
                    Text(DissmisActionText,
                        style = TextStyle(
                            color = MaterialTheme.colors.background,
                            fontWeight = FontWeight.Bold
                        ))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        ConfirmAction.invoke()
                        close.invoke()
                    }
                ) {
                    Text(confirmActionText,
                        style = TextStyle(
                            color = MaterialTheme.colors.background,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        )
    }
}