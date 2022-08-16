package com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputsPomodoro(
    text: MutableState<String>,
    value: MutableState<Float>,
    range: ClosedFloatingPointRange<Float>,
    onAdd:() -> Unit
){
    Column(){
    Column() {
        Text(
            "Name",
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(
            modifier = Modifier.height(
                15.dp
            )
        )

        Card(
            backgroundColor = Color.Transparent,
            shape = RoundedCornerShape(50.dp),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary
            ),
            elevation = 0.dp
        ) {
            TextField(value = text.value,
                onValueChange = { text.value = it },

                colors = textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent, //hide the indicator
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    textColor = Color.White
                ),

                placeholder = {
                    Text(
                        "Name",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        }
    }

    Spacer(
        modifier = Modifier.height(
            25.dp
        )
    )

    Column() {

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle
                        (
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("${String.format("%.0f", value.value)}")
                }
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary
                    )
                ) {
                    append(" min")
                }
            })
        Slider(
            value =value.value,
            valueRange = range,
            onValueChange = { value.value = it },
            colors = SliderDefaults.colors(
                thumbColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd) {
        TextButton(
            onClick = { onAdd.invoke() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text(
                "A D D ",
                style = TextStyle(
                    color = Color.White
                )
            )
        }
    }
    }
}