package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizState
import com.carloszaragoza.triviaapp.R
@Composable
fun PreQuiz(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val category = viewModel.currentCategory.value?.first()
    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Transparent
            ) {
        Column() {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween){
              Card(modifier = Modifier
                  .size(200.dp)
                  .padding(start = 10.dp, top = 20.dp),
                backgroundColor = Color.White,
              shape = CircleShape){
                  Image(painter = painterResource(id = category?.image!! ),
                      contentDescription = "${category.title} image",
                      contentScale = ContentScale.Fit,)
              }
                DifficultyPicker(difficulty = viewModel.currentDifficulty.value) {
                    viewModel.onEvent(QuizEvents.changeDifficulty(it))
                }
           }
            Column (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = category?.title.toString(),
                        style = TextStyle(
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = category?.definition.toString(),
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.White.copy(alpha = .8f),
                            fontWeight = FontWeight.Bold))
                }

                Button(onClick = { viewModel.onEvent(QuizEvents.startCountDown) },
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                        .height(50.dp),
                    interactionSource = MutableInteractionSource(),

                    border = BorderStroke(width = 2.dp, color = Color.White),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White.copy(alpha = .2f),)) {
                    Text(text = "Game",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

        }

    }
}

