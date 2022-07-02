package com.example.jettrivia2.feature_trivia.domain.model

data class QuestionItem(
    val answer: String,
    val category: String,
    val choices: List<String>,
    val question: String
)