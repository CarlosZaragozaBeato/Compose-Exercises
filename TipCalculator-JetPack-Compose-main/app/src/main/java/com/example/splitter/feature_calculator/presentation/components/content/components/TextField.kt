package com.example.splitter.feature_calculator.presentation.components.content.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.splitter.constants.Colors

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextField(
    text: MutableState<String>,
    enabled:Boolean = true,
    modifier:Modifier = Modifier,
    maxLine: Int = 1,
    onImeAction: () -> Unit = {},
    placeholder:String = "",
    opcion:Boolean = true,
    icon: ImageVector = Icons.Default.Money,
){

    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
            modifier = modifier,
            value =  text.value,
            placeholder = {Text(text = placeholder,
                modifier = Modifier
                    .fillMaxWidth(),
                style =
                    if(opcion){
                        TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            color = Colors.darkGreen,
                            textAlign = TextAlign.End)
                    }else{
                        TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Colors.darkGreen,
                        textAlign = TextAlign.End)
                    })
                   },
            onValueChange ={
                if(opcion){
                    if(text.value.length<=2){
                        text.value = it
                    }else{
                        text.value =""
                    }
                }else{
                    text.value = it
                }


            },
            enabled = enabled,
            textStyle =  if(opcion){
                TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Colors.darkGreen,
                    textAlign = TextAlign.End)
            }else{
                TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Colors.darkGreen,
                    textAlign = TextAlign.End)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Colors.veryLightGreen,
                focusedIndicatorColor = Colors.darkGreen,
                cursorColor = Colors.darkGreen
            ),
            maxLines = maxLine,
            keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }),
            leadingIcon = {
                if(opcion == false) {
                    Icon(imageVector = icon,
                        contentDescription = "Money Icon")
                }else{
            }

            })
}
