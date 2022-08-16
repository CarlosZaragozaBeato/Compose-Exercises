package com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.home.HomeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TimerCircle(
    viewModel : HomeViewModel = hiltViewModel()
){

        Card(modifier = Modifier
            .fillMaxHeight(.50f)
            .fillMaxWidth(.86f),
            backgroundColor = Color.Transparent,
            shape = CircleShape,
            border = BorderStroke(width = 5.dp, color = Color.White.copy(.8f))
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(if(viewModel.pomodoroSelected.value == null)
                     ""
                    else
                    viewModel.pomodoroSelected.value?.name.toString(),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.LightGray
                    )
                )


                Text(
                    "${String.format("%.0f",viewModel.currentTime.value)} min",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.fillMaxHeight(.3f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(Color.White.copy(.1f))
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth(viewModel.currentPosition.value)
                            .fillMaxHeight()
                            .background(Color.White.copy(.8f)),
                    )
                }
            }
        }

}