package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun BodyTitle(){
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(start = LocalSpacing.current.medium)
    ){
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 32.5.sp,
                    fontWeight = FontWeight.Bold,
                    baselineShift = BaselineShift(0.2f),

                )

                ) {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primaryVariant
                        )
                    ) {
                        append("Let's finds the best\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primaryVariant
                        )
                    ) {
                        append("food around you")
                    }
                }
            })


    }
}