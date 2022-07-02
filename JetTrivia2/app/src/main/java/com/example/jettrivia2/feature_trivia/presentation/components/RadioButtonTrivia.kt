package com.example.jettrivia2.feature_trivia.presentation.components

import android.widget.RadioButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettrivia2.feature_trivia.core.AppColors

@Composable
fun RadioButtonTrivia(
    text:String = "Answer1",
    AnswerState: MutableState<Int?>,
    updateAnswer: (Int)->Unit = {},
    it:Int = 1,
    correctState:MutableState<Boolean?>
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp, brush = Brush.linearGradient(
                    listOf(
                        AppColors.lightWhite,
                        AppColors.gray
                    )
                ),
                shape = RoundedCornerShape(33.dp)
            ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
    ){

        RadioButton(selected = AnswerState.value == it,
            onClick = {     updateAnswer(it) },
            colors = RadioButtonDefaults.colors(
                selectedColor =
                if(correctState.value == true){
                     AppColors.lightGreen
                }else{
                     AppColors.lightRed
                },
                disabledColor = if(correctState.value == true){
                    AppColors.lightGreen
                }else{
                    AppColors.lightRed
                }

            ),
            enabled = if(AnswerState.value == null) true else false
            )


            Text(text = text,
                color = AppColors.lightWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)


    }


}