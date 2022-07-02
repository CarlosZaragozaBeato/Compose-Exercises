package com.example.jettrivia2.feature_trivia.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.jettrivia2.feature_trivia.core.AppColors
import com.example.jettrivia2.feature_trivia.domain.model.QuestionItem


@Composable
fun AnswerSection(
    question: QuestionItem,
    nextQ : MutableState<Int>
){

    var index = remember{
        mutableStateOf(nextQ)
    }

    val choicesState = remember(question){
        question.choices.toMutableList()
    }


    val AnswerState = remember(question){
        mutableStateOf<Int?>(null)
    }

    val CorrectState = remember(question){
        mutableStateOf<Boolean?>(null)
    }

    val updateAnswer: (Int)->Unit  = remember(question){
        {
          AnswerState.value = it
          CorrectState.value = choicesState[it] == question.answer
        }
    }





    Column() {

        Text(text = question.question,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.3f)
                    .padding(6.dp),
            style = TextStyle(
                color = AppColors.lightWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ))

        choicesState.forEachIndexed{it, answer->
            RadioButtonTrivia(
                text = answer,
                it = it,
                updateAnswer ={
                    updateAnswer(it)
                },
                AnswerState = AnswerState,
                correctState = CorrectState
            )
        }

        ButtonAnswer(
            AnswerIndex = nextQ.value,
            nextQuestion = {

                nextQ.value +=  1
            }

        )



        
    }




}