package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.body_add

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldCustomBody(
    text:String,
    textStyle: TextStyle,
    placeholder: String,
    maxLines:Int = 1,
    onChange:(String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = {
            onChange.invoke(it)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(placeholder,
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.White.copy(.8f)
                        )) },
        maxLines = maxLines,
        textStyle =textStyle
    )
}