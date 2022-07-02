package com.example.notesappudemy.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldNote(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
                onValueChange = onTextChange,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xffF9F9F9),
                    textColor = Color(0xff4B8673),
                    focusedLabelColor = Color(0xff816797),
                    focusedIndicatorColor = Color(0xff816797)
            ),
                maxLines = maxLine,
                label = { Text(text = label)},
                keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done, ),
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