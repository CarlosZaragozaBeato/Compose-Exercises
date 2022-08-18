package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carloszaragoza.triviaapp.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent
import kotlinx.coroutines.delay
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash.utils.SplashEvents
import kotlinx.coroutines.flow.collect

@Composable
fun SplashScreen(
    onNavigate: (UiEvent.Navigate)-> Unit,
    onPopBackStack: ()-> Unit,
    viewModel:SplashViewModel = viewModel()
    ) {

    val scale = remember {
        Animatable(.1f)
    }
    LaunchedEffect(key1 = true) {


        scale.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f)
                        .getInterpolation(it)
                }
            ))

        delay(1500L)
        onPopBackStack()
        viewModel.onEvent(SplashEvents.OnHomePage)

        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }

        }
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary)
                .offset(x= 0.dp, y = (-25).dp),
            contentAlignment = Alignment.BottomCenter,
            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colors.primary)
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {

                Image(
                    painter = painterResource(id = R.drawable.splash),
                    contentDescription = "Books Image",
                    modifier = Modifier
                        .scale(scale.value)
                        .size(100.dp),
                )

                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        "Trivia App",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onPrimary
                        )
                    )
                }
            }

        }

    }
