package com.example.recipesapp.feature_recipe.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipesapp.feature_recipe.presentation.screens.home.components.CardHome
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController,
                viewModel: ViewModelMain = hiltViewModel(),
) {


    val list = viewModel.listCategories.value





    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
    ) {
            items(list.size) { index ->
                CardHome(category = list[index], navController = navController)
        }
    }

}