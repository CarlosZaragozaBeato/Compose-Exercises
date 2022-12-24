package com.carloszaragoza.groceryapp.feature_shop.presentation.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.domain.model.OrderList
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.model.ItemsList
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.OrderUseCase
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.util.OrdersEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.util.OrdersState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderUseCase: OrderUseCase,
    private val userUseCases: UserUseCases
): ViewModel(){

    var state by mutableStateOf(OrdersState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        initial()
    }

    fun onEvent(event: OrdersEvent){
        when(event) {
            is OrdersEvent.OnPop -> {
                sendUiEvent(UiEvent.OnPop)
            }
            is OrdersEvent.OnDelete -> {
                deleteItem(event.id)
            }
            is OrdersEvent.OnCompletedOrder -> {
                completeOrder()
            }
            is OrdersEvent.ToggleDialog-> {
                state.showDialog.value = !state.showDialog.value
            }
            is OrdersEvent.AddOrder -> {
                addOrder(event.item)
            }
        }
    }


    private fun addOrder(item: Item) = viewModelScope.launch(Dispatchers.IO) {

        val list = state.order?.itemsList?.itemsList
        list?.add(item)

        orderUseCase.addOrder(
            Order(
                id = state.order?.id!!,
                date = LocalDateTime.now().toString(),
                userId = state.order?.userId,
                itemsList = ItemsList(itemsList = list!!),
            )
        )
        initial()

    }

    private fun completeOrder() = viewModelScope.launch(Dispatchers.IO){
            val userLoggedIn = userUseCases.getUserLoggedIn()
            val listOf: OrderList? = userLoggedIn?.orderList
            listOf?.OrderList?.add(state.order!!)!!

               if(state.listItems.value.isNotEmpty()){
                   userUseCases.addUser(
                       User(
                           id =  state.order?.userId!!,
                           orderList = userLoggedIn.orderList.copy(
                               OrderList = listOf.OrderList
                           ),
                           password = userLoggedIn.password,
                           username = userLoggedIn.username,
                           logIn = userLoggedIn.logIn))
                   state.showDialog.value = false
                   orderUseCase.addOrder(
                       Order(
                           date = LocalDateTime.now().toString(),
                           id = state.order!!.id,
                           itemsList = ItemsList(mutableListOf()),
                           userId = state.order!!.userId
                       )
                   )
               }
                    sendUiEvent(UiEvent.OnPop)
            }



    private fun deleteItem(id:String) = viewModelScope.launch (Dispatchers.IO){
      val list:MutableList<Item> = mutableListOf()
      var condition = true
        state.isLoading = true

       state.order?.itemsList?.itemsList?.forEach{ item ->
           list.add(item)
           if(condition){
                if(item.id == id){
                    list.remove(item)
                    condition = false
                }
            }
          }

        orderUseCase.addOrder(
            Order(
                id = state.order?.id!!,
                date = LocalDateTime.now().toString(),
                userId = state.order?.userId,
                itemsList = ItemsList(itemsList = list),
            )
        )

        initial()
        state.isLoading = false

      }

    private fun initial(){
        initialEvents()
        viewModelScope.launch {
            delay(100)
            InitialCalculation()
        }
    }

    private fun InitialCalculation(){
        viewModelScope.launch {
            CalculateTotalOrders()
            calculateDeliver()
            sumBill()
        }
    }



    private fun calculateDeliver(){
        state.delivery.value = 0.0
        if(state.order?.itemsList?.itemsList?.isEmpty()!!){
            state.delivery.value = 0.0
            return
        }
        if(state.order?.itemsList?.itemsList?.size!!>=10) {
            state.delivery.value = 0.0
            return
        }
            state.delivery.value = state.listItems.value.size*1.5
    }

    private fun CalculateTotalOrders(){
        state.totalItems.value = 0.0
        if(state.listItems.value.isEmpty()){
            state.totalItems.value = 0.0
        }
        state.listItems.value.forEach{
            state.totalItems.value += it.price * state.countOrders.get(it.id)!!
        }
    }

    private fun sumBill(){
        state.total.value = 0.0
        state.total.value = state.totalItems.value + state.delivery.value
    }
    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
    private fun onFilterOrder(order: List<Item>){
        state.countOrders = order.groupingBy { item->
            item.id
        }.eachCount().filter { it.value >= 1 }

        state.listItems.value = Item.listOfIngredients.filter { itm->
            state.countOrders.any{ map->
                itm.id == map.key
            }
        }
    }
     private fun initialEvents(){
        viewModelScope.launch (Dispatchers.Main){
            orderUseCase.getOrders().collect{
              state = state.copy(order = it.first())
                onFilterOrder(state.order?.itemsList?.itemsList!!)
            }
            delay(100)
        }
    }
}