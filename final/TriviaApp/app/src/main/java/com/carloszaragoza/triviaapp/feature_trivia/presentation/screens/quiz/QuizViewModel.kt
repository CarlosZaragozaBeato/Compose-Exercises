package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.triviaapp.feature_trivia.data.data_source.DataOrException
import com.carloszaragoza.triviaapp.feature_trivia.data.repository.QuestionsRepository
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.category_model.Category
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model.Questions
import com.carloszaragoza.triviaapp.feature_trivia.presentation.navigation.Routes
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.utils.*
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuestionsRepository,
    private val savedStatedHandle: SavedStateHandle
): ViewModel() {


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val currentStateQuiz = mutableStateOf(QuizState.PREQUIZ.name)
    val openDialog = mutableStateOf(false)
    val currentCategory = mutableStateOf<List<Category>?>(null)

    val currentDifficulty = mutableStateOf(QuizDifficulty.easy.name)
    val timer = mutableStateOf(3)



    private val _questions = mutableStateOf<DataOrException<Questions,Boolean, Exception>>(
        DataOrException(null, false, Exception(""))
    )
    val questions = _questions
    val indexQuestion = mutableStateOf(0)
    val quizOver = mutableStateOf(false)
    val answersOrdered = mutableStateOf<List<String>>(emptyList())
    val isAnswered = mutableStateOf(false)
    val currentScore = mutableStateOf(0)

    init {
        searchCategory(savedStatedHandle.get<String>("category")!!)
    }



    fun onEvent(event:QuizEvents){
        when(event){
            is QuizEvents.changeDialog ->{
                openDialog.value = event.value
            }
            is QuizEvents.startCountDown -> {
                resetValues()
                currentStateQuiz.value = QuizState.COUNTDOWN.name
                countdown()
                viewModelScope.launch {
                    getQuestions(category = CategoryConverter(currentCategory.value?.first()?.title.toString()))
                    answersOrdered.value = CollectAnswers(
                        _questions.value.data?.results?.get(indexQuestion.value)?.correct_answer!!,
                        _questions.value.data?.results?.get(indexQuestion.value)?.incorrect_answers!!)
                }

            }
            is QuizEvents.changeDifficulty -> {
                currentDifficulty.value = event.value.name
            }
            is QuizEvents.checkAnswer ->{
                isAnswered.value = true
                if(_questions.value.data?.results?.get(indexQuestion.value)?.correct_answer == event.value){
                    currentScore.value ++
                }
                viewModelScope.launch {
                   delay(3000)
                    if(indexQuestion.value<9){
                        indexQuestion.value ++
                        answersOrdered.value = CollectAnswers(
                            _questions.value.data?.results?.get(indexQuestion.value)?.correct_answer!!,
                            _questions.value.data?.results?.get(indexQuestion.value)?.incorrect_answers!!)
                        isAnswered.value = false
                    }else{
                        quizOver.value = true
                    }
                }
            }
            is QuizEvents.toHomePage -> {
                sendUiEvent(UiEvent.Navigate(Routes.HOME.name))
            }
        }
    }



    fun countdown(){
        viewModelScope.launch {
            while(timer.value>0){
                delay(1000)
                timer.value -=1
            }
            currentStateQuiz.value = QuizState.QUIZ.name
        }
    }
fun resetValues(){
    timer.value = 3
    indexQuestion.value = 0
    answersOrdered.value = emptyList()
    currentScore.value = 0
    isAnswered.value = false
}

    suspend fun getQuestions(category:Int?){
        _questions.value = repository.getQuestions(difficulty = currentDifficulty.value, category = category!!, amount = 10 )
    }


    private fun searchCategory(id:String){
        currentCategory.value = Category.listCategories.filter {
            it.id == id
        }
    }


    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }


}