package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.welcome

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.navigation.Routes
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.welcome.components.FinishButton
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.welcome.components.PagerScreen
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.OnboardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()){
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primary)) {
        HorizontalPager(
            modifier = Modifier.weight(8f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onboardingPage =  pages[position])
        }


        HorizontalPagerIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .weight(1f)
                .padding(horizontal = 10.dp),

            indicatorHeight = 5.dp,
            indicatorWidth = 25.dp,
            activeColor = MaterialTheme.colors.secondary,
            inactiveColor = Color.Gray.copy(.6f),
            spacing = 5.dp,


            pagerState = pagerState
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Routes.HOME.name)
            welcomeViewModel.saveOnBoardingState(completed = true)

        }
}
}





