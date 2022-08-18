package com.carloszaragoza.noteapp.feature_note.presentation.screens.notes.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.add_edit_note.util.AddEditNoteEvent

@Composable
fun CustomTextField(
    text:String,
    onValueChange:(String) -> Unit,
    placeholder: String
){
    TextField(value = text,
        onValueChange = {onValueChange(it)},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.secondary,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primaryVariant,
        ),
        singleLine = true,
        maxLines  = 1,
        textStyle = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary,
        ),
        placeholder = { Text(placeholder,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary,
            ))})
}