package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizState

@Composable
fun QuizQuestions(
    viewModel: QuizViewModel = hiltViewModel()
){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .fillMaxHeight(.20f),
                contentAlignment = Alignment.Center

            ){
                Card(
                    border = BorderStroke(width = 2.dp, color = Color.White),
                    backgroundColor = Color.White.copy(.2f),
                    modifier = Modifier
                        .fillMaxWidth(.6f)

                ){
                  Box(modifier = Modifier
                      .fillMaxWidth()
                      .height(150.dp),
                    contentAlignment = Alignment.BottomCenter){
                      Image(painter = painterResource(id = viewModel.currentCategory.value?.first()?.image!!),
                          contentDescription = "Category Image",
                          contentScale = ContentScale.Fit
                          ,)

                  }
                }

            }
            QuizQuestionsAndAnswers(viewModel = viewModel)
            QuizDialog(openDialog = viewModel.quizOver.value ,
                title = "Your Punctuation",
                question = "${viewModel.currentScore.value} of 10",
                confirmActionText = "Reset",
                DissmisActionText = "Go to Home Page",
                ConfirmAction = {
                    viewModel.currentStateQuiz.value = QuizState.PREQUIZ.name
                },
                DissmisOperation = {
                    viewModel.onEvent(QuizEvents.toHomePage)
                },
                close = {
                    viewModel.quizOver.value = false
                }

            )
        }

}