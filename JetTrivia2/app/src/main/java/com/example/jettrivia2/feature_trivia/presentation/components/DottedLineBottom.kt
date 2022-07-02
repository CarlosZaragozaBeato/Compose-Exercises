package com.example.jettrivia2.feature_trivia.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.example.jettrivia2.feature_trivia.core.AppColors

@Composable
fun DottedLineBottom(
    pathEffect: PathEffect
){
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)){
        drawLine(
            color = AppColors.lightRed,
            start = Offset(0f, 0f),
            end =  Offset(size.width, 0f),
            pathEffect = pathEffect,

        )


    }
}