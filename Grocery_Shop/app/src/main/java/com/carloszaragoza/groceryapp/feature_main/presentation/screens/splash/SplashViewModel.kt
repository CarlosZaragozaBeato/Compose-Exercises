package com.carloszaragoza.groceryapp.feature_main.presentation.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: UserUseCases
):ViewModel() {

    private val _route = mutableStateOf(Routes.LOGIN.name)
    val route: State<String> = _route

    fun getUserLoggedIn(){
        viewModelScope.launch(Dispatchers.IO) {
             useCase.getUserLoggedIn().let { user->
                if(user != null){
                    _route.value = Routes.HOME.name
                }
            }
        }
    }
}