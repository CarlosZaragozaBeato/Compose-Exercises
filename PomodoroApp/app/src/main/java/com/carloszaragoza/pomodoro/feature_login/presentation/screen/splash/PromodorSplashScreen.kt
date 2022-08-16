package com.carloszaragoza.pomodoro.feature_login.presentation.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.snap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.screen.splash.SplashViewModel
import com.carloszaragoza.pomodoro.feature_login.presentation.screen.splash.utils.SplashEvents
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.firstColor
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.fourthColor
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.secondColor
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.thirdColor
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.utils.UiEvent
import kotlinx.coroutines.delay

@Composable
fun PromodoroSplahScreen(
    onNavigate: (UiEvent.onNavigate) -> Unit,
    viewModel: SplashViewModel = viewModel(),
    onPopBackStack:()-> Unit
){

    val scaleFirstLetter = remember {
        Animatable(1f)
    }
    val scaleSecondLetter= remember {
        Animatable((-1f))
    }
    LaunchedEffect(key1 = true) {
    delay(1000)

        scaleFirstLetter.animateTo(targetValue = 0f,
            animationSpec =  snap(
                delayMillis =25
            )
        )
        scaleSecondLetter.animateTo(targetValue = 0f,
            animationSpec = snap(
                delayMillis = 25,
            ))

        delay(1500L)
        onPopBackStack()
        viewModel.onEvent(SplashEvents.onNavigate)

        viewModel.uiEvents.collect { event ->
            when (event) {
                is UiEvent.onNavigate -> onNavigate(event)
                else -> Unit
            }

        }
    }


   Box(
       modifier = Modifier
           .fillMaxSize()
           .background(MaterialTheme.colors.primary),
       contentAlignment = Alignment.Center,
   ) {
       Text(

           buildAnnotatedString {
               withStyle(
                   style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                       fontSize = 40.sp
                   )
               ) {
                   withStyle(
                       style = SpanStyle(
                            color = firstColor,
                           baselineShift = BaselineShift(0f)
                       )
                   ) {
                       append("PO")
                   }
               withStyle(
                   style = SpanStyle(
                        color = secondColor,
                       baselineShift = BaselineShift(scaleFirstLetter.value)
                   )
               ) {
                   append("MO")
               }

               withStyle(
                   style = SpanStyle(
                        color = thirdColor,
                       baselineShift = BaselineShift(scaleSecondLetter.value)
                   )
               ) {
                   append("DO")
               }
               withStyle(
                   style = SpanStyle(
                        color = fourthColor,
                       baselineShift = BaselineShift(0f)
                   )
               ) {
                   append("RO")
               }
           }})
   }
}

