package com.carloszaragoza.groceryapp.feature_main.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_main.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel(){

    fun saveOnBoardingState(completed:Boolean){
        viewModelScope.launch {
            repository.saveOnBoardingState(completed = completed)
        }
    }
}