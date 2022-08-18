package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun DrawLineCustom(){
    val color =MaterialTheme.colors.primaryVariant.copy(.5f)
    Canvas(Modifier.fillMaxWidth(.5f).height(1.dp)) {
        val height = size.height
        val width = size.width

        drawLine(
            start = Offset(x= 0f, y = 0f),
            end = Offset(x = width, y = height),
            color = color,
            strokeWidth = 16.0f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)
        )
    }
}