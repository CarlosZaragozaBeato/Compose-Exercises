package com.carloszaragoza.layoutexample.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.carloszaragoza.layoutexample.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    val id: MutableState<String> = mutableStateOf("")
    val current_recipe:MutableStateFlow<Recipe?> = MutableStateFlow(null)
    init {
        savedStateHandle.get<String>("foodId").let { food_id ->
            id.value = food_id!!
        }
        filterRecipes()
    }


    private fun filterRecipes (){
       Recipe.list_of_reciper.forEach {
           if(it.id == id.value) current_recipe.value =  it
        }
    }

}