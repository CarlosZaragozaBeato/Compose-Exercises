package com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.OnBoardingPage

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage
) {

    Column(
        verticalArrangement =Arrangement.SpaceBetween,
        modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f),
            contentAlignment = Alignment.BottomCenter
        ){
            Image(
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = "Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = LocalSpacing.current.medium)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,

            ){
            Text(onBoardingPage.title,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.extraSmall))
            Text(onBoardingPage.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
