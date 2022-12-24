package com.carloszaragoza.groceryapp.feature_main.presentation.screens.splash


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    onNavigation:(String)-> Unit,
    onPop: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val imageScale = remember {
        Animatable(.1f)
    }

    LaunchedEffect(key1 = true) {

        viewModel.getUserLoggedIn()

        imageScale.animateTo(targetValue = 1f,
            animationSpec =  tween(
                delayMillis =25
            )
        )
        delay(1500)
        onPop.invoke()
        onNavigation(viewModel.route.value)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = R.drawable.food_purchase_bag),
                contentDescription = "Food purchase bag",
                modifier = Modifier.scale(imageScale.value),
                contentScale = ContentScale.Fit)
        }



        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = MaterialTheme.typography.h1.fontWeight
                    )
                ) {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.onBackground,
                            baselineShift = BaselineShift(0f)
                        )
                    ) {
                        append("Shop")
                    }
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colors.onBackground.copy(.5f))
                    ) {
                        append("App")
                    }

                }})
    }
}