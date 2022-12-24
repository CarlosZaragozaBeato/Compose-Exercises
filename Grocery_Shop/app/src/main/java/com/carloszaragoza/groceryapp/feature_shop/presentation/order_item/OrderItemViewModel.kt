package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.OrderUseCase
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.util.OrderItemState
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.util.OrderItemsEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderItemViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){


     val state = mutableStateOf(OrderItemState())


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init{
            state.value.currentId = savedStateHandle.get<Int>("index")!!
            getUser()
            initialEvents()
    }

     fun onEvent(event: OrderItemsEvents){
        when(event){
            is OrderItemsEvents.onPop ->{
                sendUiEvent(UiEvent.OnPop)
            }
        }
    }
    private fun getUser(){
       viewModelScope.launch (Dispatchers.IO){
           userUseCases.getUserLoggedIn.invoke().let {
               state.value.currentUser.value = it
           }
           state.value.selectedOrder.value = state.value.currentUser.value?.orderList?.OrderList?.get(state.value.currentId)
       }
    }
    private fun calculateTotalOrders() {
        state.value.totalItems.value = 0.0
        if (state.value.listItems.value.isEmpty()) {
            state.value.totalItems.value = 0.0
        }
        state.value.listItems.value.forEach {
            state.value.totalItems.value += it.price * state.value.countOrders.get(it.id)!!
        }
        if(state.value.selectedOrder.value?.itemsList?.itemsList?.size!! <3){
            state.value.totalItems.value += state.value.listItems.value.size * 1.5
        }
    }
    private fun onFilterOrder(){
        state.value.countOrders =
            state.value.selectedOrder.value?.itemsList?.itemsList?.groupingBy { item->
                item.id
            }?.eachCount()?.filter { it.value >= 1 }!!

        state.value.listItems.value = Item.listOfIngredients.filter { itm->
            state.value.countOrders.any{ map->
                itm.id == map.key
            }
        }
    }
    private fun initialEvents(){
        viewModelScope.launch (Dispatchers.Main){
                delay(200)
                onFilterOrder()
                calculateTotalOrders()
        }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}