package com.carloszaragoza.triviaapp.feature_trivia.data.network

import com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model.Questions
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface TriviaApi{

    @GET(value = "api.php")
    suspend fun getTriviaQuestions(
        @Query("amount") amount:Int,
        @Query("category") category:Int,
        @Query("difficulty") difficulty:String
    ): Questions


}


