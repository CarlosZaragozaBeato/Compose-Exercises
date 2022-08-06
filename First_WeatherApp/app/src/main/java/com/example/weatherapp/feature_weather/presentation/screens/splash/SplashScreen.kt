package com.example.weatherapp.feature_weather.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {


    val scale = remember{
        Animatable(0f)
    }

    val width = remember{
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block ={
        scale.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(3f)
                        .getInterpolation(it)
                }
            ))
        width.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
               ))
        delay(1500L)
        navController.popBackStack()
        navController.navigate(RouteScreens.CHOOSE_COUNTRY.name)

    } )

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
        ){


                Text(text = "WeatherApp",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .scale(scale.value)
                        .padding(start = LocalSpacing.current.extraSmall,
                            end = LocalSpacing.current.extraSmall,
                            bottom = LocalSpacing.current.medium)
                )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(width.value)
                    .height(15.dp)
                    .background(MaterialTheme.colors.primaryVariant),
            )
            }


           }



        }



