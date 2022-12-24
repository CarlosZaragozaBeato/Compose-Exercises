package com.carloszaragoza.groceryapp.feature_main.presentation.screens.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing


@Composable
fun PasswordTextField(
    text: String,
    modifier: Modifier,
    placeHolder:String,
    onTextChanged: (text: String) -> Unit,
    passwordValidate: Boolean,
    keyboardAction: KeyboardActions,
    imeAction: ImeAction
) {


    var passwordVisibility by remember{
        mutableStateOf(false)
    }

    val icon = if(passwordVisibility)
                    Icons.Default.VisibilityOff
                else
                    Icons.Default.Visibility

      TextField(value = text,
          onValueChange =onTextChanged,
          placeholder = {Text(placeHolder)},
          trailingIcon = {
          IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
              Icon(imageVector =icon,
                  contentDescription ="Visibility" )
          }
      },
          keyboardActions = keyboardAction,
          keyboardOptions = KeyboardOptions(imeAction = imeAction),
          modifier = modifier,
          shape = RoundedCornerShape(LocalSpacing.current.small),
          visualTransformation =  if(passwordVisibility)
                                    VisualTransformation.None
                                else
                                    PasswordVisualTransformation(),
          colors = TextFieldDefaults.textFieldColors(
              backgroundColor = MaterialTheme.colors.onBackground.copy(.03f),
              cursorColor = if (passwordValidate) MaterialTheme.colors.onBackground
                            else Color.Red.copy(.5f),
              textColor =   if (passwordValidate) MaterialTheme.colors.onBackground
                            else Color.Red.copy(.5f),
              trailingIconColor = if (passwordValidate) MaterialTheme.colors.onBackground
                                  else Color.Red.copy(.5f),
              placeholderColor =  if (passwordValidate) MaterialTheme.colors.onBackground
                                  else Color.Red.copy(.5f),
              focusedIndicatorColor = Color.Transparent,
              unfocusedIndicatorColor = Color.Transparent
      ))


}