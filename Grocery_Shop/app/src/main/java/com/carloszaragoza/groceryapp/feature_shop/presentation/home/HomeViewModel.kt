package com.carloszaragoza.groceryapp.feature_shop.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.model.ItemsList
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.OrderUseCase
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.util.HomeEvents
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.util.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.log
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userUseCase: UserUseCases,
    private val orderCase: OrderUseCase
):ViewModel() {

    val state = MutableStateFlow(HomeState())
    val orders : MutableState<Order?> = mutableStateOf(null)
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        initialEvents()
    }

    fun onEvent(event: HomeEvents){
        when(event){
            is HomeEvents.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(event.route))
            }
            is HomeEvents.AddItem -> {
                addOrder(event.item)
            }
        }
    }



    private fun addOrder(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            if(state.value.orders.value == null){
                orderCase.addOrder(
                    Order(
                        date = LocalDateTime.now().toString(),
                        userId = userUseCase.getUserLoggedIn.invoke()?.id,
                        itemsList = ItemsList(itemsList = mutableListOf(item)),
                    )
                )

            }else{
                val newList = state.value.orders.value!!.itemsList?.itemsList
                newList?.add(item)

                orderCase.addOrder(
                    Order(
                        date = LocalDateTime.now().toString(),
                        userId = userUseCase.getUserLoggedIn.invoke()?.id,
                        itemsList = ItemsList(itemsList = newList!!),
                        id = state.value.orders.value!!.id
                    )
                )
            }
            orderCase.getOrders().collect{
                state.value.orders.value = it.first()

            }

        }
    }


    private fun getRandomItems() {
        val list: MutableState<List<Item>> = mutableStateOf(emptyList())
        while(state.value.randomItems.size<=9){
             var number = Random.nextInt(0, Item.listOfIngredients.size)
             var item = Item.listOfIngredients[number]

            list.value += item
            state.value.randomItems=  list.value.distinct()
           }

    }

    private fun initialEvents(){
        viewModelScope.launch(Dispatchers.IO) {
            getRandomItems()
            getCurrentUser()
        }
        getOrders()
    }

    private fun getCurrentUser(){
        userUseCase.getUserLoggedIn.invoke().let {
            state.value.user = it
        }
    }
    private fun getOrders(){
      viewModelScope.launch (Dispatchers.Main){
          orderCase.getOrders.invoke().let {
              it?.collect { list ->
                  if (list.isNotEmpty() && list !=null){
                      state.value.orders.value = list.first()
                  }
              }
          }
      }
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}