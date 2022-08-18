package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components.*
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizState
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent

@Composable
fun QuizScreen(
    onPopBackStack: () -> Unit,
    viewModel: QuizViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
){
    val scaffoldState = rememberScaffoldState()
    val category = viewModel.currentCategory.value?.first()
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            QuizAppBar(
                onPopBackStack = onPopBackStack,
                viewModel = viewModel
            )
        },
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .background(brush = Brush.linearGradient(category?.gradient!!)),
    ) {
        when(viewModel.currentStateQuiz.value){
             QuizState.PREQUIZ.name->{
                 PreQuiz(viewModel = viewModel)
             }
            QuizState.COUNTDOWN.name->{
                CountDownQuiz(viewModel = viewModel)
            }
            QuizState.QUIZ.name-> {
                QuizQuestions(
                    viewModel = viewModel
                )
            }
            else-> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("Error!!",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = Color.White
                        ))
                }
            }
        }
    }
}