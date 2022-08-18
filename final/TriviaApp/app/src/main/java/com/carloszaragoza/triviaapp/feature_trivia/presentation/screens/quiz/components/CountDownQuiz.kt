package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel

@Composable
fun CountDownQuiz(viewModel: QuizViewModel){

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
        Card(
            shape = CircleShape,
            backgroundColor = Color.Transparent,
            border = BorderStroke(width = 5.dp, color = Color.White),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight(.45f)
        ) {
            Box(modifier =Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center){
                Text(text = viewModel.timer.value.toString(),
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 60.sp,
                        color = Color.White,
                    ),)
            }

        }
    }
}