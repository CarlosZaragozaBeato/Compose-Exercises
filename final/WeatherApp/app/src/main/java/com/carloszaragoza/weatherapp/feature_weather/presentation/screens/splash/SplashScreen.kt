package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.splash.utils.SplashEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: (UiEvent.onNavigate) -> Unit,
    onPop: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){

    LaunchedEffect(key1 = true, block ={
        viewModel.scale.value.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(3f)
                        .getInterpolation(it)
                }
            ))
        viewModel.width.value.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
            )
        )
        delay(1500L)

        viewModel.onEvent(SplashEvents.OnPop)
        viewModel.onEvent(SplashEvents.OnNavigate)

        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.onNavigate-> onNavigate(event)
                is UiEvent.popBackStack-> onPop()
            }
        }
    } )

    Scaffold{

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.large),
            contentAlignment = Alignment.BottomStart){
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        color = MaterialTheme.colors.primaryVariant,
                        fontWeight = FontWeight.Bold),) {
                        append("Weather")
                    }
                    withStyle(style = SpanStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Light)
                    ) {
                        append(" App")
                    }

                },
                modifier = Modifier
                    .scale(viewModel.scale.value.value)
                    .padding(bottom = LocalSpacing.current.large)

            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(viewModel.width.value.value)
                    .height(15.dp)
                    .background(MaterialTheme.colors.primaryVariant),
            )
        }

    }



}