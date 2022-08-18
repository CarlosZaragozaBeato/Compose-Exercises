package com.carloszaragoza.triviaapp.feature_trivia.data.repository

import com.carloszaragoza.triviaapp.feature_trivia.data.data_source.DataOrException
import com.carloszaragoza.triviaapp.feature_trivia.data.network.TriviaApi
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model.Questions
import javax.inject.Inject

class QuestionsRepository @Inject constructor(
    private val api:TriviaApi
){

    private val dataOrExceptionQuestions = DataOrException<Questions,Boolean,Exception>()

    suspend fun getQuestions(amount:Int, category: Int, difficulty:String)
    : DataOrException<Questions,Boolean,Exception>{
        try{
            dataOrExceptionQuestions.loading = true
            dataOrExceptionQuestions.data =
                api.getTriviaQuestions(amount = amount, category = category, difficulty =difficulty )


            if(dataOrExceptionQuestions.data != null){
                dataOrExceptionQuestions.loading = false
            }
        }catch (ex:Exception){
            dataOrExceptionQuestions.e = ex
        }
        return dataOrExceptionQuestions
        }
    }