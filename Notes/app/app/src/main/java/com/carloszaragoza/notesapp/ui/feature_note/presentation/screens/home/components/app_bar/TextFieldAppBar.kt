package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldAppBar(
    text:String,
    onValueChange: (String) -> Unit
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colors.onBackground)
            .fillMaxWidth()
    ){
        TextField(value = text, onValueChange = {
            onValueChange.invoke(it)
        },
            maxLines = 1,
            modifier = Modifier,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.background,
                textColor = MaterialTheme.colors.background,
            ))
    }
}