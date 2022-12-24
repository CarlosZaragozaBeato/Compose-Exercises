package com.carloszaragoza.groceryapp.feature_shop.presentation.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.groceryapp.feature_main.domain.model.OrderList
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.user.components.user_appbar.UserAppBar
import com.carloszaragoza.groceryapp.feature_shop.presentation.user.util.UserEvents

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onPop: () -> Unit,
    onNavigate:(UiEvent.OnNavigate) -> Unit
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnPop -> onPop()
                is UiEvent.OnNavigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

  if(!state.isLoading.value){
      Scaffold(
          backgroundColor = MaterialTheme.colors.background,
          topBar = {
              UserAppBar(
                  onPop = {viewModel.onEvent(UserEvents.OnPop)},
                  username = state.user?.username.toString()){
                viewModel.onEvent(UserEvents.OnLogOut)
              }
          }
      ){

            Column() {
                TitleOrders(modifier = Modifier)
                ListOrders(state.user?.orderList!!){
                    viewModel.onEvent(UserEvents.OnNavigate(it))
                }
            }

      }
  }else{
      Box(modifier = Modifier
          .fillMaxSize()
          .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center){
          CircularProgressIndicator(
              color = MaterialTheme.colors.onBackground
          )
      }
  }
}

@Composable
fun ListOrders(
    orderList: OrderList,
    onTap:(Int) -> Unit) {


    LazyColumn(){
        itemsIndexed(orderList?.OrderList!!){index, order->

            val date = order.date?.split("T")

            Row(
                modifier = Modifier
                    .padding(LocalSpacing.current.medium)
                    .fillMaxWidth()
                    .clickable{ onTap.invoke(index) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(){
                  Text((index+1).toString(),
                      style = MaterialTheme.typography.h4,
                      color = MaterialTheme.colors.onBackground)
                  Spacer(modifier = Modifier.width(LocalSpacing.current.medium))
                  Column{

                      Text(date?.get(0).toString(),
                          style = MaterialTheme.typography.body1,
                          color = MaterialTheme.colors.onBackground)
                      Text(date?.get(1)?.split(".")?.get(0).toString(),
                          style = MaterialTheme.typography.body1,
                          color = MaterialTheme.colors.onBackground)
                  }
              }
                Box(contentAlignment = Alignment.Center) {

                    Text("Quantity ${order.itemsList?.itemsList?.size.toString()}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground)
                }
            }
        }
    }
}
@Composable
fun TitleOrders(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.medium),
        contentAlignment = Alignment.CenterEnd
    ){
        Text("ORDERS",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = modifier)
    }
}
