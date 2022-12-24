package com.carloszaragoza.groceryapp.feature_login.presentation.login.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing

@Composable
fun TextFieldCustom(
    text:String,
    onTextChanged:(String) -> Unit,
    placeholder:String,
    modifier : Modifier,
    userValidate:Boolean,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction
) {
    TextField(
        value = text,
        onValueChange = { txt ->
            onTextChanged.invoke(txt)
        },
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        ),
        modifier = modifier,
        shape = RoundedCornerShape(LocalSpacing.current.small),
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.onBackground.copy(.03f),
            cursorColor = if (userValidate) MaterialTheme.colors.onBackground
            else Color.Red.copy(.5f),
            textColor =   if (userValidate) MaterialTheme.colors.onBackground
            else Color.Red.copy(.5f),
            trailingIconColor = if (userValidate) MaterialTheme.colors.onBackground
            else Color.Red.copy(.5f),
            placeholderColor =  if (userValidate) MaterialTheme.colors.onBackground
            else Color.Red.copy(.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}