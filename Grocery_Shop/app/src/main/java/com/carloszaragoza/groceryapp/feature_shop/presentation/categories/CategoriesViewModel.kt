package com.carloszaragoza.groceryapp.feature_shop.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.categories.util.CategoriesEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class CategoriesViewModel:ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: CategoriesEvent){
        when(event){
            is CategoriesEvent.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(event.route))
            }
            is CategoriesEvent.OnPop -> {
                sendUiEvent(UiEvent.OnPop)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}