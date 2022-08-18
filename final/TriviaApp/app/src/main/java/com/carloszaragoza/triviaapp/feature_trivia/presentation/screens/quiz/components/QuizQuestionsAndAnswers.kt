package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.QuizEvents

@Composable
fun QuizQuestionsAndAnswers(
    viewModel: QuizViewModel = hiltViewModel()
){

    val questions = viewModel.questions.value.data?.results?.get(viewModel.indexQuestion.value)


    val cleanQuestion = HtmlCompat.fromHtml(questions?.question.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if(viewModel.questions.value.data?.results?.isNotEmpty()!!) {
            Column() {
                Text(
                    "Questions ${viewModel.indexQuestion.value + 1} of 10",
                    style = TextStyle(
                        color = Color.White.copy(.5f),
                    )
                )

                Text(
                    "Score ${viewModel.currentScore.value} of 10",
                    style = TextStyle(
                        color = Color.White.copy(.5f),
                    )
                )
                Text(
                    text = cleanQuestion,
                    style = TextStyle(
                        fontSize = 19.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
            }
            LazyColumn(

            ) {
                items( viewModel.answersOrdered.value){ question ->
                    AnswerItem(
                        question = question,
                        correct = questions?.correct_answer,
                        answered = viewModel.isAnswered.value
                    ) {
                        viewModel.onEvent(QuizEvents.checkAnswer(it))
                    }
                }
            }
        } else{
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
        }
    }


@Composable
fun AnswerItem(question: String,
               correct:String?,
               answered:Boolean,
                onAction: (String) -> Unit) {


    val cleanAnswer = HtmlCompat.fromHtml(question, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()


    Button(onClick = { if(!answered) onAction(question)},
       colors =  ButtonDefaults.buttonColors(
           backgroundColor =Color(0xff393E46)
       ),
    modifier = Modifier
        .fillMaxWidth(.9f)

        .padding(vertical = 2.5.dp)) {
       Text(text = cleanAnswer,
        color = if(answered){
                    if(question==correct)Color.Green.copy(.8f) else {Color.Red.copy(.8f)}}
            else {Color.White})
   }
}
