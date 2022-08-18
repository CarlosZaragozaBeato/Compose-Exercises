package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils


sealed class HomeEvents{

    data class OnQuizPage(val route:String): HomeEvents()
    data class OnSearchFilterCategory(val category:String):HomeEvents()
    data class OnFilterCategory(val filter:String):HomeEvents()

}