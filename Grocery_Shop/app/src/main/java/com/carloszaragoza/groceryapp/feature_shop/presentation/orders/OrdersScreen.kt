package com.carloszaragoza.groceryapp.feature_shop.presentation.orders

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components.ItemCard
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components.OrderInfo
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components.OrdersDialog
import com.carloszaragoza.groceryapp.feature_shop.presentation.orders.util.OrdersEvent

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun OrdersScreen(
    viewModel: OrdersViewModel = hiltViewModel(),
    onPop:() -> Unit
) {

    val state = viewModel.state

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnPop -> onPop()
                else -> Unit
            }
        }
    }

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState:BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                           OrderInfo(
                               delivery = viewModel.state.delivery.value,
                               total = viewModel.state.total.value,
                               totalItems =viewModel.state.totalItems.value,
                               onComplete = {
                                   viewModel.onEvent(OrdersEvent.ToggleDialog)
                               }
                           )
            },
            sheetBackgroundColor = Color.Transparent,
            sheetPeekHeight = LocalSpacing.current.extraLarge,
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    title = { Text("Current Order",
                            color = MaterialTheme.colors.onBackground,
                            style =  MaterialTheme.typography.h5)},
                    navigationIcon = {
                        IconButton(onClick = { viewModel.onEvent(OrdersEvent.OnPop) }) {
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription ="Go to home page",
                                tint = MaterialTheme.colors.onBackground)
                        }
                    })
            }) {
        OrdersDialog(openDialog = state.showDialog.value,
                    confirmActionText = "Confirm",
                    title = "Confirm Your Orders",
                    question = "Are you Sure?",
                    DissmisActionText = "Cancel",
                    DissmisOperation = {viewModel.onEvent(OrdersEvent.ToggleDialog)},
                    ConfirmAction = {
                        viewModel.onEvent(OrdersEvent.OnCompletedOrder)
                    })


            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ){
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(LocalSpacing.current.small,),
                    cells = GridCells.Fixed(2)){
                items(viewModel.state.listItems.value){ item->
                    ItemCard(
                        onDelete = {viewModel.onEvent(OrdersEvent.OnDelete(it))},
                        item = item,
                        onAddItem = {viewModel.onEvent(OrdersEvent.AddOrder(it))},
                        quantity = viewModel.state.countOrders.get(item.id)!!
                    )
                }
            }
        }
    }
}