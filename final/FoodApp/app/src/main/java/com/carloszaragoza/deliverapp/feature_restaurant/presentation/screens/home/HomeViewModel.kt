package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category.Category
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.location.Location
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.recipe.Recipe
import com.carloszaragoza.deliverapp.feature_restaurant.domain.repository.Repository
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){




    //**Channel between viewModel and presentation**//
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    //*DropDownMenu Variables*//
    val currentLocation: MutableState<Location> = mutableStateOf(
                                                                Location.listOfObjects.first())
    var expanded =  mutableStateOf(false)
    
    var textfieldSize = mutableStateOf(Size.Zero)
    
    
    //*Current Chart*//
     val currentOrder = repository.getOrders()


    //**Categories Filtered**//
    val listCategories = mutableStateOf<List<Category>>(emptyList())
    val currentCategory = mutableStateOf<Category?>(null)

    val listOfRecipes = mutableStateOf<List<Recipe>>(emptyList())



    fun onEvent(event: HomeEvents){
        when(event){
            is HomeEvents.OnChangeLocation -> {
                currentLocation.value = event.location
            }
            is HomeEvents.FilterList -> {
                filterCategories()
            }
            is HomeEvents.OnChangeCategory -> {
                currentCategory.value = event.category
            }
            is HomeEvents.FilterRecipe -> {
                filterRecipes()
            }
            is HomeEvents.OnAddOrder -> {
                AddOrder(event.order)
            }
            is HomeEvents.onNavigate -> {
                sendUiEvent(UiEvent.onNavigation(event.route))
            }
        }
    }



    private fun AddOrder(order:Order){
        viewModelScope.launch(Dispatchers.IO) {

           repository.insertOrder(order)
        }
    }


    private fun filterCategories(){
        listCategories.value = Category.listOfCategories.filter{ id->
            currentLocation.value.categoriesId.any {
                id.id == it
            }
        }
        currentCategory.value = listCategories.value.first()
    }

    private fun filterRecipes(){
        listOfRecipes.value = Recipe.listOfRecipes.filter{ id->
            currentCategory.value?.recipes?.any {
                id.id == it
            }!!
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch{
         _uiEvent.send(event)
        }
    }



}

