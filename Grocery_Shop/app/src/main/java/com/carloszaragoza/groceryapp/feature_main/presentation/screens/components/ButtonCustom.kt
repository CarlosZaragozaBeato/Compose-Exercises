package com.carloszaragoza.groceryapp.feature_login.presentation.login.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing


@Composable
fun ButtonCustom(
    onAction:()-> Unit,
    modifier:Modifier,
    text:String
) {

    Button(onClick = { onAction.invoke() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onBackground,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = LocalSpacing.current.default
        )) {
        Text(text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background)
    }
}