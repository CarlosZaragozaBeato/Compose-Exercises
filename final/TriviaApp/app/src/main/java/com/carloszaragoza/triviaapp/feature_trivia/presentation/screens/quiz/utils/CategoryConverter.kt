package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils

fun CategoryConverter(category:String):Int?{
    return when(category){
        "General Knowledge" -> 9
        "Entertainment: Books" ->  10
        "Entertainment: Films" -> 11
        "Entertainment: Music" -> 12
        "Television" -> 14
        "Entertainment: Video Games" -> 15
        "Entertainment: Board Games" -> 16
        "Science & Nature" -> 17
        "Science: Computers" -> 18
        "Science: Mathematics" -> 19
        "Mythology" -> 20
        "Sports" -> 21
        "Geography" -> 22
        "History" -> 23
        "Politics" -> 24
        "Art" -> 25
        "Celebrities" -> 26
        "Animals" -> 27
        "Vehicles" -> 28
        "Entertainment: Comics" -> 29
        "Entertainment: Japanese Anime & Manga" -> 31
        "Entertainment: Cartoon & Animations" -> 32
        else ->{
            null
        }
    }
}