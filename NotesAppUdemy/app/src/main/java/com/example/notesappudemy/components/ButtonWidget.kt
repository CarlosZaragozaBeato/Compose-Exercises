package com.example.notesappudemy.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWidget(
    modifier : Modifier = Modifier,
    OnClick : ()->Unit = {},
    text : String,
    enable:Boolean = true,
    colors: ButtonColors,
    elevation: ButtonElevation,
    border: BorderStroke,
    textColor:Long
    ){
    Button(onClick = { OnClick.invoke() },
            enabled = enable,
            colors = colors,
            elevation = elevation,
            border = border,
            shape = RoundedCornerShape(corner = CornerSize(2.dp)),
            modifier = modifier) {
        Text(text = text,
            color = Color(textColor))
    }
}