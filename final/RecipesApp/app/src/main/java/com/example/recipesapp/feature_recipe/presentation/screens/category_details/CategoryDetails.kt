package com.example.recipesapp.feature_recipe.presentation.screens.category_details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.screens.category_details.components.CardItem
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@SuppressLint("StateFlowValueCalledInComposition", "CoroutineCreationDuringComposition")
@Composable
fun CategoryScreen(navController: NavHostController,
                    category: String?,
                   id:String?,
                    viewModelMain: ViewModelMain = hiltViewModel()){
    viewModelMain.filterCategory(id!!)

    val list = viewModelMain.listFilterLocalRecipes.collectAsState().value

    if(list.isNullOrEmpty()){
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center){
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                color = Colors.redPastel
            )
        }
    }else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical =5.dp),
        ){
            items(list.size){ index->
                CardItem(item = list[index]){
                    navController.navigate(RouteScreen.MEAL.name+"/${list[index].id}/${list[index].title}")
                }
            }
        }
    }
}