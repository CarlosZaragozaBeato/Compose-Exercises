package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.recipe_body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.HomeViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun RecipeBody(
    viewModel: HomeViewModel = hiltViewModel(),
    ubication:String,

){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = LocalSpacing.current.medium, bottom = LocalSpacing.current.medium)
    ){
        Text("Result ${viewModel.listOfRecipes.value.size}",
            style = TextStyle(
                color = MaterialTheme.colors.primaryVariant.copy(.7f),
                fontSize = 14.sp),
            modifier = Modifier.padding(bottom = LocalSpacing.current.medium))
        LazyRow(
            modifier = Modifier
                .height(280.dp)
        ){
            items(viewModel.listOfRecipes.value){ item ->
                RecipeItem(
                    item = item,
                    ubication = ubication
                ){
                    viewModel.onEvent(HomeEvents.OnAddOrder(it))
                }
            }
        }
    }
}