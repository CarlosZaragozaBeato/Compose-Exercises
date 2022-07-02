package com.example.jettrivia2.feature_trivia.presentation.trivia_app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jettrivia2.feature_trivia.domain.data.DataOrException
import com.example.jettrivia2.feature_trivia.domain.model.QuestionItem
import com.example.jettrivia2.feature_trivia.domain.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository
):ViewModel(){

    val data :MutableState<DataOrException<ArrayList<QuestionItem>,
            Boolean,
            Exception>> =
        mutableStateOf(DataOrException(null,
            true,Exception("")))



    init{
        getAllQuestion()
    }


    private fun getAllQuestion(){

        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }

}