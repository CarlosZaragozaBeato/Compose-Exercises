package com.example.jettrivia2.feature_trivia.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.jettrivia2.feature_trivia.core.AppColors

@Preview
@Composable
fun QuestionCount(
    count:Int =0,
    offCount:Int = 100
){
    Row(
        modifier = Modifier
            .padding(20.dp)
    ) {

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 30.sp,
                color = AppColors.gray,
                fontWeight = FontWeight.Bold
            )){
                append("Question: $count /")
            }
            withStyle(style = SpanStyle(
                fontSize = 15.sp,
                color = AppColors.gray,
                fontWeight = FontWeight.Bold

            )){
                append(offCount.toString())
            }
        })

    }



}


