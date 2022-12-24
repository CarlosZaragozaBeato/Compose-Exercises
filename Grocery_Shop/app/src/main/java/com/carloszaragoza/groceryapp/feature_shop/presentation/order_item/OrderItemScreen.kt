package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.components.GridItems
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.components.OrderItemInfo
import com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.util.OrderItemsEvents


@Composable
fun OrderItem(
    viewModel: OrderItemViewModel = hiltViewModel(),
    onPop:()-> Unit) {

    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnPop -> onPop()
                else -> Unit
            }

        }
    }
    if(state.value.listItems.value.isNotEmpty()) {
        Scaffold(
            topBar = {
              TopAppBar(
                  backgroundColor = Color.Transparent,
                  elevation = 0.dp,
                  title = {
                      Text("${state.value.currentUser.value?.username}")
                  },
                  navigationIcon = {
                      IconButton(onClick = { viewModel.onEvent(OrderItemsEvents.onPop) }) {
                          Icon(imageVector = Icons.Default.ArrowBack ,
                              contentDescription ="Go to Orders Page" )
                      }
                  }
              )
            }
        ) {
            Column {
                OrderItemInfo(
                    total = state.value.totalItems.value,
                    quantity =state.value.selectedOrder.value?.itemsList?.itemsList?.size!! ,
                    currentId = state.value.currentId+1,
                )
            GridItems(itemList = viewModel.state.value.listItems.value,
                        count =  state.value.countOrders)
                }
            }
    }else{
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator(
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}