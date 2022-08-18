package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils

fun CollectAnswers(answer:String, incorrectAnswers:List<String>):List<String>{
    var collectedAnswers:MutableList<String> = incorrectAnswers as MutableList<String>
    collectedAnswers.add(answer)
    collectedAnswers.shuffle()
    return collectedAnswers
}