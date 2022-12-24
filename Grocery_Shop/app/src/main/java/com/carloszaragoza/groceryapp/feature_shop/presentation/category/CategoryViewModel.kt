package com.carloszaragoza.groceryapp.feature_shop.presentation.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.UserUseCases
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category
import com.carloszaragoza.groceryapp.feature_shop.domain.model.ItemsList
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.OrderUseCase
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.util.CategoryEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.util.CategoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val useCase: OrderUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val userUseCase: UserUseCases
):ViewModel(){

     var state  by mutableStateOf(CategoryState())



    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        initialEvents()
        viewModelScope.launch {
            useCase.getOrders().collect{
               if(it.isNotEmpty()){
                   state.currentOrder = it.first()
               }
            }

        }
    }

    fun onEvent(event: CategoryEvent){
        when(event){
            is CategoryEvent.OnNavigate -> {
                sendUiEvent(UiEvent.OnNavigate(event.route))
            }
            is CategoryEvent.AddItem -> {
                addOrder(event.item)
            }
        }
    }


     private fun addOrder(item: Item) {
         viewModelScope.launch(Dispatchers.IO) {
             if(state.currentOrder == null){
                 useCase.addOrder(
                     Order(
                         date = LocalDateTime.now().toString(),
                         userId = userUseCase.getUserLoggedIn.invoke()?.id,
                         itemsList = ItemsList(itemsList = mutableListOf(item)),
                     )
                 )
                 useCase.getOrders().collect{
                     state.currentOrder = it.first()
                 }
             }else{

                 val newList = state.currentOrder!!.itemsList?.itemsList
                 newList?.add(item)

                 useCase.addOrder(
                     Order(
                         date = LocalDateTime.now().toString(),
                         userId = userUseCase.getUserLoggedIn.invoke()?.id,
                         itemsList = ItemsList(itemsList = newList!!),
                         id = state.currentOrder!!.id
                     )
                 )
             }
         }
     }


    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch{
            _uiEvent.send(event)
        }
    }


    private fun initialEvents(){

           state = state.copy(
               currentCategoryId =  savedStateHandle.get<String>("category").toString()
           )
           state = state.copy(
               listOfItems = Item.listOfIngredients.filter { item->
                   item.category == state.currentCategoryId
               }
           )
           state = state.copy(
               currentCategory = Category.listOfCategories.filter { category ->
                   category.id == state.currentCategoryId
               })



       }
    }
