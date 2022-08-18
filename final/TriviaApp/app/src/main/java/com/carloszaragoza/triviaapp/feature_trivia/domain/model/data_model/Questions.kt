package com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model

data class Questions(
    val response_code: Int,
    val results: List<Result>
)