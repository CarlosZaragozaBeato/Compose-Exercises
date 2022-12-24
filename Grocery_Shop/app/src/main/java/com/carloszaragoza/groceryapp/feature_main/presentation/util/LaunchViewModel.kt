package com.carloszaragoza.groceryapp.feature_main.presentation.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_main.data.repository.DataStoreRepository
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Routes.WELCOME.name)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect{completed->
                if(completed){
                    _startDestination.value = Routes.SPLASH.name
                }else{
                    _startDestination.value = Routes.WELCOME.name
                }
            }
        }
    }
}