package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    text:String,
    onValueChange:(String) -> Unit,
    placeholder: String,
    onDone:() -> Unit
){
    TextField(value = text,
        onValueChange = {onValueChange(it)},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            textColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primary,
        ),
        singleLine = true,
        maxLines  = 1,
        shape = CircleShape,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
        ),
        keyboardActions = KeyboardActions(onDone = {onDone.invoke()}),
        modifier = Modifier
            .fillMaxWidth(.6f),
        placeholder = { Text(placeholder,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            ))})
}