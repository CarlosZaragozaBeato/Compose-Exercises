package com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ButtonActionsPomodoro(
    onAction: () -> Unit,
    text:String
){
    Button(onClick = {onAction.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier.fillMaxWidth(.25f)
    ) {
        Text(text.uppercase(),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}