package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizState

@Composable
fun QuizAppBar(
    onPopBackStack: () -> Unit,
    viewModel: QuizViewModel = hiltViewModel()
) {

            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,
                modifier = Modifier
                    .fillMaxHeight(.15f),
                title = {
                    Text(text = viewModel.currentCategory.value?.first()?.title.toString(),
                        style = TextStyle(
                            fontSize = 25.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(QuizEvents.changeDialog(true)) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            tint = Color.White
                        )
                    }
                }
            )
           QuizDialog(
               openDialog = viewModel.openDialog.value,
               ConfirmAction = onPopBackStack,
               DissmisActionText = "Cancel",
               confirmActionText = "Go Back",
               title = "Exit",
               question = "Are you sure?",
               close = {viewModel.onEvent(QuizEvents.changeDialog(false))},
               DissmisOperation = {viewModel.onEvent(QuizEvents.changeDialog(false))})
    }
