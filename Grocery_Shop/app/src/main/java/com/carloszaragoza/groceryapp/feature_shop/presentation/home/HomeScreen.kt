package com.carloszaragoza.groceryapp.feature_shop.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body.CategoriesList
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body.ShoppingList
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body.SuggestedItemsList
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.top_bar.TopBarHome
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.util.HomeEvents

@Composable
fun HomeScreen(
    viewModel:HomeViewModel = hiltViewModel(),
    onNavigate:(UiEvent.OnNavigate)->Unit
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnNavigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopBarHome( goToProfile = {viewModel.onEvent(HomeEvents.OnNavigate(Routes.USER.name))},
                        goToSearch = {viewModel.onEvent(HomeEvents.OnNavigate(Routes.SEARCH.name))})
        }
    ){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ){

             state.value.user?.let {

                 ShoppingList(listOfOrders =if(state.value.orders.value?.itemsList?.itemsList == null)
                                                emptyList()
                                            else
                                                state.value.orders.value?.itemsList?.itemsList!!.toList()){
                     viewModel.onEvent(HomeEvents.OnNavigate(Routes.ORDERS.name))
                 }
                 CategoriesList(
                     onNavigate = { category ->
                                  viewModel.onEvent(HomeEvents.OnNavigate(
                                      route = "${Routes.CATEGORY.name}/${category}"
                                  ))
                     },
                     toCategoryScreen = {
                         viewModel.onEvent(HomeEvents.OnNavigate(Routes.CATEGORIES.name))
                     }
                 )
                 SuggestedItemsList(items = state.value.randomItems){ item ->
                        viewModel.onEvent(HomeEvents.AddItem(item))
                 }

             }
            if(state.value.user == null){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                     CircularProgressIndicator()
                   }
                }


        }
    }
}