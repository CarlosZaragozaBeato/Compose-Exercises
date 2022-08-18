package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.location.Location
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.HomeViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.home_app_bar.TitleIcons
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun HomeScreenTopBar(
    viewModel: HomeViewModel = hiltViewModel(),
    currentChart: State<List<Order>>
){
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        modifier = Modifier.height(100.dp),
    ){

        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(end = LocalSpacing.current.small)
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.7f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start

            ) {
                DropMenuCustom(
                    expanded = viewModel.expanded,
                    itemsList = Location.listOfObjects,
                    textfieldSize = viewModel.textfieldSize,
                ) { location ->
                    viewModel.onEvent(HomeEvents.OnChangeLocation(location))
                }
                TextLocation(
                    city = viewModel.currentLocation.value.city,
                    country = viewModel.currentLocation.value.country
                )
            }


            TitleIcons(numberOfOrders = currentChart.value.size){
                viewModel.onEvent(HomeEvents.onNavigate(it))
            }
        }
    }
}