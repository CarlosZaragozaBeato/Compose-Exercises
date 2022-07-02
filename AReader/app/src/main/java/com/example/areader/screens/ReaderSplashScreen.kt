package com.example.areader.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import com.example.areader.components.ReaderLogo
import com.example.areader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun ReaderSplashScreen(navController: NavHostController) {

    val scale = remember{
        Animatable(0.05f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 0.9f,
                        animationSpec = tween(
                            delayMillis = 300,
                            easing = {
                                OvershootInterpolator(3f)
                                    .getInterpolation(it)
                            }
                        ), )

        delay(2000L)
      /*  if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
            navController.navigate(ReaderScreens.LoginScreen.name
            )
        }else{
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)

        }*/
        navController.navigate(ReaderScreens.ReaderHomeScreen.name)


    }

    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(2.dp, Color.LightGray)
    ){
        Column(
            modifier = Modifier
                .padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            ReaderLogo()

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "\"Read. Change. Yourself\"",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray)


        }
    }


}

