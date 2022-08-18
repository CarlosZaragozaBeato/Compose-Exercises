package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.welcome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.OnboardingPage


@Composable
fun PagerScreen(
    onboardingPage: OnboardingPage = OnboardingPage.First
){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.65f),
            contentAlignment = Alignment.BottomStart
        ){
            Image(
                painter = painterResource(id = onboardingPage.image),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,

        ){
            Text(onboardingPage.title,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(onboardingPage.description,
                style = TextStyle(
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal)
            )
        }
    }
}
