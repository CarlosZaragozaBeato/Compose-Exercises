package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils

import com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model.Questions

data class QuestionsState(
    val isLoading:Boolean = false,
    val questions:Questions? = null,
    val error:String = ""
)