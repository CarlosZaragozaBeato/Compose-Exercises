package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizDifficulty
import java.util.*


@Composable
fun DifficultyPicker(
    difficulty: String,
    onDifficultyChange: (QuizDifficulty) -> Unit
){
    val  suggestions = listOf<QuizDifficulty>(QuizDifficulty.easy,
                                                QuizDifficulty.medium,
                                                QuizDifficulty.hard)
    var expanded by  remember{mutableStateOf(false)}
    var textfieldSize by  remember{
        mutableStateOf(Size.Zero)
    }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, end = 10.dp)
    ) {
        OutlinedTextField(
            value = difficulty.capitalize(Locale.ROOT),
            onValueChange ={},
            modifier = Modifier
                .fillMaxWidth(.7f)
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()
                }
                .clickable{
                    expanded = !expanded
                },
            enabled = false,
            trailingIcon = {

                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Drop Down",
                        tint = Color.White)

            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.White,
                disabledIndicatorColor =  Color.White,
                disabledLabelColor =   Color.White,
                ),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White)
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onDifficultyChange(label)
                }){
                    Text(text = label.name.capitalize(Locale.ROOT),
                        style = TextStyle(
                            color = Color(0xff393E46)
                        )
                    )
                }

            }
        }
    }
}