package com.example.recipesapp.feature_recipe.presentation.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.screens.category_details.components.CardItem
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoriteScreen(navController: NavController,viewModelMain: ViewModelMain = hiltViewModel()){
    val list = viewModelMain.listFilterLocalRecipes.collectAsState().value

   LaunchedEffect(key1 =list ){
       viewModelMain.getNotes()
       viewModelMain.filterFavorites()
   }

    if(list.isNullOrEmpty() ){
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
        LazyColumn() {
            items(list.size) { index ->
                CardItem(item = list[index]) {
                    navController.navigate(
                        RouteScreen.FAVORITES_ITEM.name+"/${list[index].id}/${list[index].title}")
                }
            }

        }

    }
}