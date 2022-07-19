package com.example.splitter.feature_calculator.presentation.components.content.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitter.constants.Colors

@Preview
@Composable
fun ButtonTip(
    text:String = "",
    onAction: ()->Unit = {}
){




    Button(onClick = {onAction.invoke()},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Colors.darkGreen,
            ),
        modifier = Modifier
            .height(50.dp)
            .padding(2.dp)) {
        Text(text = text,
            color = Colors.White)
    }
}