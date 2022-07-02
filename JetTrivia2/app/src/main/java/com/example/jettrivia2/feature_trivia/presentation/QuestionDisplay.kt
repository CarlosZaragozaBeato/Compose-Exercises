package com.example.jettrivia2.feature_trivia.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import com.example.jettrivia2.feature_trivia.core.AppColors
import com.example.jettrivia2.feature_trivia.presentation.components.DottedLineBottom
import com.example.jettrivia2.feature_trivia.presentation.components.ProgressBar
import com.example.jettrivia2.feature_trivia.presentation.components.QuestionCount
import com.example.jettrivia2.feature_trivia.presentation.trivia_app.QuestionViewModel
import androidx.compose.ui.unit.dp
import com.example.jettrivia2.feature_trivia.presentation.components.AnswerSection


@Composable
fun QuestionDisplay(
    viewModel: QuestionViewModel
){
    val questions = viewModel.data.value.data?.toMutableList()

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(1f,1f),0f)

    var questionIndex = remember{
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = AppColors.mDarkPurple
    ) {

        Column {

            if(questionIndex.value>=3){
                ProgressBar(score = questionIndex.value)
            }

            QuestionCount(
                count = questionIndex.value,
                offCount = questions!!.size
            )

            DottedLineBottom(pathEffect = pathEffect)

            AnswerSection(
                question = viewModel.data.value.data!!.get(questionIndex.value),
                nextQ = questionIndex
            )
        }
    }
}