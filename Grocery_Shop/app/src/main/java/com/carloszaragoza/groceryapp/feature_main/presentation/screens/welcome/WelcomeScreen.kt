package com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome

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
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome.components.FinishButton
import com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome.components.PagerScreen
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.OnBoardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel()) {

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,)

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        HorizontalPager(
            count = pages.size,
            modifier = Modifier.weight(8f),
            state = pagerState,
            verticalAlignment = Alignment.Top) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
    HorizontalPagerIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
            .weight(1f)
            .padding(horizontal = LocalSpacing.current.medium),
            indicatorHeight = 5.dp,
            indicatorWidth = 25.dp,
            activeColor = MaterialTheme.colors.onBackground,
            inactiveColor = Color.Gray.copy(.6f),
            spacing = 5.dp,
            pagerState = pagerState)

        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Routes.LOGIN.name)
            viewModel.saveOnBoardingState(completed = true)

        }
    }

}