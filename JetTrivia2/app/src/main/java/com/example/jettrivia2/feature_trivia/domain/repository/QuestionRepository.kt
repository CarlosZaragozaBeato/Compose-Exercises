package com.example.jettrivia2.feature_trivia.domain.repository

import android.util.Log
import com.example.jettrivia2.feature_trivia.data.network.QuestionApi
import com.example.jettrivia2.feature_trivia.domain.data.DataOrException
import com.example.jettrivia2.feature_trivia.domain.model.QuestionItem
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {


    private val dataOrException = DataOrException<ArrayList<QuestionItem>,
                                                    Boolean,
                                                            Exception>()


    suspend fun getAllQuestions():DataOrException<ArrayList<QuestionItem>,
                                                    Boolean,
                                                    Exception>{

        try{
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if(dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        }catch (ex:Exception){
            dataOrException.e = ex
            dataOrException.e!!.localizedMessage?.let{ Log.d("Error",  it)}
        }
        return dataOrException
    }
}