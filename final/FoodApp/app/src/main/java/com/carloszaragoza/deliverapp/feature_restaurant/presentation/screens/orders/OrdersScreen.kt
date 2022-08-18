package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.calculate_orders.CalculateOrdersPrice
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.components.orders_app_bar.BodyOrders
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.components.orders_app_bar.OrdersAppBar
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.UiEvent

@Composable
fun OrderScreen(
    viewModel: OrdersViewModel = hiltViewModel(),
    pop:()->Unit){
    val orders = viewModel.orders.collectAsState(initial = emptyList())
    val configuration = LocalConfiguration.current
    configuration.screenHeightDp.dp

    LaunchedEffect(key1 = orders.value){

            viewModel.onEvent(OrdersEvents.Onfilter(orders.value))
            viewModel.calculateDeliver(orders.value.size)
            viewModel.calculateTotal()
            viewModel.sumBill()

    }

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event->
            when(event){
                is UiEvent.onPopBackStack -> pop.invoke()
                else -> Unit
            }
        }
    }

   Scaffold(
       topBar = {
           OrdersAppBar(viewModel = viewModel)
       }
   ){
       Column(
           modifier = Modifier
               .fillMaxHeight(),
           verticalArrangement = Arrangement.SpaceBetween
       ){
           BodyOrders()



               CalculateOrdersPrice(
                   delivery = viewModel.deliveryPrice.value,
                   totalItems = viewModel.totalItemsPrice.value,
                   total = viewModel.totalPrice.value
               ){
                   viewModel.onEvent(OrdersEvents.OnCompleteOrders)
               }

       }
   }
}