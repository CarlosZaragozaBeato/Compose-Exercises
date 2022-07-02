package com.example.jettrivia2.feature_trivia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettrivia2.feature_trivia.core.AppColors



@Composable
fun ProgressBar(score: Int = 12){


    val progressFactor = remember(score){
        mutableStateOf(score * 0.005f)
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(45.dp)
            .border(width = 5.dp, brush = Brush.linearGradient(
                colors = listOf(
                    AppColors.gray, AppColors.orange
                )
            ),
                shape = RoundedCornerShape(33.dp))
            .clip(shape = RoundedCornerShape(topEndPercent = 50,
                                            topStartPercent = 50,
                                            bottomStartPercent = 50,
                                            bottomEndPercent = 50))
            .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,


        ){
        Button( elevation = null,
                contentPadding = PaddingValues(1.dp),
                onClick = {},
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth(progressFactor.value)
                    .background(Brush.linearGradient(
                        colors = listOf(
                            AppColors.lightWhite,AppColors.orange
                        )
                    )),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    disabledBackgroundColor = Color.Transparent
                ))
                {
                    Text(text = (score*10).toString(),
                        modifier = Modifier
                            .clip( shape  = RoundedCornerShape(23.dp)
                            )
                            .fillMaxHeight(0.87f)
                            .fillMaxWidth()
                            .padding(6.dp),
                        color = AppColors.lightWhite,
                        textAlign = TextAlign.Center)

                }

        }
    }


