package com.carloszaragoza.groceryapp.feature_shop.presentation.category

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.components.app_bar.CategoryAppBar
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.components.body.CategoryBody
import com.carloszaragoza.groceryapp.feature_shop.presentation.category.util.CategoryEvent

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit
) {

    val state = viewModel.state

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
            CategoryAppBar(currentCategory = state.currentCategory.first(),
                modifier = Modifier,
                onPop = {viewModel.onEvent(CategoryEvent.OnNavigate(Routes.HOME.name))})
        }) {

        CategoryBody(
            listOfItems = state.listOfItems,
            currentCategory = state.currentCategory.first()){ item ->
            viewModel.onEvent(CategoryEvent.AddItem(item))
        }
    }
}