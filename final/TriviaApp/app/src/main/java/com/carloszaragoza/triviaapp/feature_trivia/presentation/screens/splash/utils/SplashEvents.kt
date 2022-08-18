package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.splash.utils

sealed class SplashEvents{

    object OnHomePage:SplashEvents()
    object OnPopBackStack:SplashEvents()
}
