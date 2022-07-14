package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.expenseapp.feature_expense.core.Colors

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun TextFieldTransaction(
    modifier: Modifier = Modifier,
    text: String ="TEXTo",
    label: String = "TEXTO",
    maxLine: Int = 1,
    onImeAction: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit = {},

){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange = onTextChange,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Colors.lightBrow,
            textColor = Colors.orange,
            focusedLabelColor = Colors.white,
            focusedIndicatorColor = Colors.white,
            cursorColor = Colors.white
        ),
        maxLines = maxLine,
        label = { Text(text = label,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done, keyboardType = keyboardType ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        modifier = modifier,
        textStyle = TextStyle(
            fontSize = 20.sp,

            ))
}