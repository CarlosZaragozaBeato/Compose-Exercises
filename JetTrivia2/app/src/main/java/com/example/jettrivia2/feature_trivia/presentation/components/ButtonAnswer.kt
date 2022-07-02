package com.example.jettrivia2.feature_trivia.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jettrivia2.feature_trivia.core.AppColors

@Preview
@Composable
fun ButtonAnswer(
    AnswerIndex: Int =0,
    nextQuestion:(Int) -> Unit = {}

){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(.5f)
            ,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.lightRed
            ),
            onClick = {
                nextQuestion(AnswerIndex)
            }) {
            Text(text = "Next",
                color = AppColors.mDarkPurple)
        }
    }

}