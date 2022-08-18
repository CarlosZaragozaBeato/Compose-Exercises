package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.components.orders_app_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.components.IconButtonCustom
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.OrdersViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun OrdersAppBar(
    viewModel: OrdersViewModel = hiltViewModel()
){
    TopAppBar(
        modifier = Modifier.padding(LocalSpacing.current.small),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButtonCustom(
                icon = Icons.Default.ArrowBack,
                modifier = Modifier,
                description = "Go to the Home page",
                action = {
                    viewModel.onEvent(OrdersEvents.OnPop)
                }
            )
        },
        title = {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(end = LocalSpacing.current.medium)
            ){
                Text("My Cart",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center)
                )
            }
        }
    )
}