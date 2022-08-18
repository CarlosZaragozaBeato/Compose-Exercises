package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils

sealed class QuizEvents {
    object startCountDown: QuizEvents()
    data class changeDialog(val value:Boolean): QuizEvents()
    data class changeDifficulty(val value:QuizDifficulty): QuizEvents()
    data class checkAnswer(val value:String):QuizEvents()
    object toHomePage:QuizEvents()
    object onReset:QuizEvents()

}
