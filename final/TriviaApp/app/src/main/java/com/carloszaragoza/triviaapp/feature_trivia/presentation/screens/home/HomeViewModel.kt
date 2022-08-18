package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.triviaapp.feature_trivia.data.repository.QuestionsRepository
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.category_model.Category
import com.carloszaragoza.triviaapp.feature_trivia.presentation.navigation.Routes
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.SearchState
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: QuestionsRepository,
): ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val searchState = mutableStateOf(SearchState.CLOSED)
    val searchValue = mutableStateOf("")

    val listFiltered = mutableStateOf<List<Category>>(Category.listCategories)




    fun onEvent(event: HomeEvents){
        when(event){
            is HomeEvents.OnQuizPage ->{
                sendUiEvent(UiEvent.Navigate(Routes.QUIZ.name+"/${event.route}"))
            }

            is HomeEvents.OnSearchFilterCategory ->{
                searchValue.value = event.category
                listFiltered.value = Category.listCategories.filter{
                    it.title.uppercase().contains(event.category.uppercase())
                }
            }
        }
    }

    fun updateSearchState(newState:SearchState){
        searchState.value = newState
    }

    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }

    }

}