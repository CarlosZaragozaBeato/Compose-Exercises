package com.example.recipesapp.feature_recipe.presentation.screens.meal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesapp.feature_recipe.presentation.screens.meal.components.MealScreenInfo
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MealScreen(
    id: String?,
    title: String?,
    viewModelMain: ViewModelMain = hiltViewModel()
){
    val item = viewModelMain.meal.collectAsState().value?.first()

    LaunchedEffect(key1 =item ){
        viewModelMain.filterMeal(id!!)
    }



    Column {
        if (viewModelMain.meal.value.isNullOrEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center){
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    color = Colors.redPastel
                )
            }
        } else {
            MealScreenInfo(item!!)
        }
    }
    
}

