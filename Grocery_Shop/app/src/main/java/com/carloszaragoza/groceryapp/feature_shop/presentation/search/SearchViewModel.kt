package com.carloszaragoza.groceryapp.feature_shop.presentation.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category
import com.carloszaragoza.groceryapp.feature_shop.domain.model.ItemsList
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.OrderUseCase
import com.carloszaragoza.groceryapp.feature_shop.presentation.search.util.SearchEvents
import com.carloszaragoza.groceryapp.feature_shop.presentation.search.util.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val orderUseCase: OrderUseCase
): ViewModel() {

    val state = mutableStateOf(SearchState())

    private val _filterText = mutableStateOf("")
    val filterText: MutableState<String> = _filterText

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        initialState()
    }

    fun onEvent(event: SearchEvents){
         when(event){
             is SearchEvents.OnNavigate -> {
                 sendUiEvent(UiEvent.OnNavigate(event.route))
             }
             is SearchEvents.OnPop -> {
                 sendUiEvent(UiEvent.OnPop)
             }
             is SearchEvents.OnChangeFilerText -> {
                 filterLists(event.text)
             }
             is SearchEvents.OnAddItem -> {
                 addOrder(event.item)
             }

         }
     }


    private fun initialState(){
        viewModelScope.launch (Dispatchers.IO){
            orderUseCase.getOrders.invoke().collect{
                state.value.order.value = it.first()
            }
        }
    }

    private fun addOrder(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {

            if(state.value.order.value == null){
                orderUseCase.addOrder(
                    Order(
                        date = LocalDateTime.now().toString(),
                        userId = userUseCases.getUserLoggedIn.invoke()?.id,
                        itemsList = ItemsList(itemsList = mutableListOf(item)),
                    )
                )

            }else{
                val newList = state.value.order.value!!.itemsList?.itemsList
                newList?.add(item)

                orderUseCase.addOrder(
                    Order(
                        date = LocalDateTime.now().toString(),
                        userId = userUseCases.getUserLoggedIn.invoke()?.id,
                        itemsList = ItemsList(itemsList = newList!!),
                        id = state.value.order.value!!.id
                    )
                )
            }
        }
    }

    private fun filterLists(text:String){
        viewModelScope.launch {
            _filterText.value = text

            state.value.listItems.value = Item.listOfIngredients.filter {
                it.name.toLowerCase().contains(text.toLowerCase())
            }
            state.value.listCategories.value = Category.listOfCategories.filter {
                it.name.toLowerCase().contains(text.toLowerCase())
            }

        }


    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }





}