package com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun PomodoroDialog(
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
            onDismissRequest = {close.invoke()},
            title = {
                Text(text = title,
                    style = TextStyle(
                        color = Color(0xff222831),
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            text = {
                Text(text = question,
                    style = TextStyle(
                        color = Color(0xff222831),
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
                            color = Color(0xff393E46),
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
                            color = Color(0xff393E46),
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        )
    }
}