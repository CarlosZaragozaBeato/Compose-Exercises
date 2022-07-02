package com.example.jettrivia2.feature_trivia.data.network

import com.example.jettrivia2.feature_trivia.domain.model.Question
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface QuestionApi {

    @GET("world.json")
    suspend fun getAllQuestions(): Question
}