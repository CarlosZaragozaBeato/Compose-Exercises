package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.R
import com.carloszaragoza.notesapp.ui.navigation.Routes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onPop: () -> Unit,
    OnNavigate: (String) -> Unit
) {

    val scaleText = remember{
       Animatable(0f)
    }
    val scaleImage = remember{
        Animatable(0.25f)
    }

    LaunchedEffect(key1 = true, block ={
        scaleImage.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
            ))
        delay(100)
        scaleText.animateTo(targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(1.1f)
                        .getInterpolation(it)
                }
            ))

        delay(1500L)
        if(viewModel.status.value != null){
            onPop.invoke()
            if(viewModel.loggedIn.value)
                OnNavigate.invoke("${Routes.HOME.name}?status=${viewModel.status.value!!.Status}")
           else
                OnNavigate.invoke("${Routes.ACCESS.name}?status=${viewModel.status.value!!.Status}")

       }else{
            onPop.invoke()
            if(viewModel.loggedIn.value)
                OnNavigate.invoke("${Routes.HOME.name}?status=500")
            else
                OnNavigate.invoke("${Routes.ACCESS.name}?status=500")
        }
    } )


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f)
                .scale(scaleImage.value),
                contentAlignment = Alignment.Center){
                Image(painter = painterResource(id = R.drawable.splash) ,
                    contentDescription = "Splash Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
            Box(modifier = Modifier
                .scale(scaleText.value)
                .fillMaxWidth(.5f)
                .height(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.onBackground)
               ,
                contentAlignment = Alignment.Center){
                Text("Notes",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .scale(scaleText.value))
            }
        }
    }


}