package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.recipe.Recipe
import com.carloszaragoza.deliverapp.feature_restaurant.domain.repository.Repository
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    val orders = repository.getOrders()

    val listOfOrders = mutableStateOf<List<Recipe>>(emptyList())

    val countOrders = mutableStateOf<Map<String, Int>>(emptyMap())

    val list = mutableStateOf<List<Order>>(emptyList())
    val listFiltered = mutableStateOf<List<Order>>(emptyList())

    val deliveryPrice = mutableStateOf<Double>(0.0)
    val totalItemsPrice = mutableStateOf<Double>(0.0)
    val totalPrice = mutableStateOf(0.0)

init {
    viewModelScope.launch {
        orders.collect{
            list.value = it
        }
    }
}



    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: OrdersEvents){
        when(event){
            is OrdersEvents.OnPop -> {
                sendUiEvent(UiEvent.onPopBackStack)
            }
            is OrdersEvents.Onfilter ->{
                onFilterOrder(event.listOrders)
            }
            is OrdersEvents.OnDeleteItem ->  {
                deleteItem(event.itemId)
            }
            is OrdersEvents.OnAddItem -> {
                addItem(event.order)
            }
            is OrdersEvents.OnCompleteOrders -> {
                deleteAllOrders()
            }
        }
    }


    fun deleteAllOrders() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }


    fun onFilterOrder(order:List<Order>){
        countOrders.value = order.groupingBy {
            it.recipeId
        }.eachCount().filter { it.value >= 1 }

        listOfOrders.value = Recipe.listOfRecipes.filter { rcp ->
            countOrders.value.any{ map->
                rcp.id == map.key
            }
        }
    }

    fun addItem(order: Order)= viewModelScope.launch(Dispatchers.IO) {
        repository.insertOrder(order)
        }

    fun calculateDeliver(numberOfItems:Int){
        if(numberOfItems == 0){
            deliveryPrice.value = 0.0
        }
        deliveryPrice.value = numberOfItems*1.5
    }

    fun calculateTotal(){
    if(listOfOrders.value.size == 0){
        totalItemsPrice.value = 0.0
    }
        listOfOrders.value.forEach{
            totalItemsPrice.value = it.price * countOrders.value.get(it.id)!!
        }
    }

    fun sumBill(){
        totalPrice.value = totalItemsPrice.value + deliveryPrice.value
    }


    private fun deleteItem(id:String) = viewModelScope.launch(Dispatchers.IO) {

            listFiltered.value = list.value.filter { ord ->
                ord.recipeId == id
            }
            repository.deleteOrder(listFiltered.value.first().id)
        }
    private fun sendUiEvent(event: UiEvent) = viewModelScope.launch {
            _uiEvent.send(event)
        }




}