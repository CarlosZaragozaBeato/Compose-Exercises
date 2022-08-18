package com.example.recipesapp.feature_recipe.presentation.screens.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain
import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.presentation.screens.meal.components.TitleMeal
import com.example.recipesapp.feature_recipe.presentation.screens.settings.components.ItemFilter
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModelMain: ViewModelMain = hiltViewModel()
){

    val listOptions = viewModelMain.listOfOptions.collectAsState()


    if(listOptions.value.isNullOrEmpty()){
        LinearProgressIndicator()
    }else {
        val gluten = remember{
            mutableStateOf(listOptions.value?.get(0)?.check)
        }
        val lactose = remember{
            mutableStateOf(listOptions.value?.get(1)?.check)
        }
        val vegetarian = remember{
            mutableStateOf(listOptions.value?.get(2)?.check)
        }
        val vegan = remember{
            mutableStateOf(listOptions.value?.get(3)?.check)
        }

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleMeal(title = "Adjust your meal Selection")
            Spacer(modifier = Modifier.height(20.dp))

            ItemFilter(
                title = listOptions.value?.get(0)?.title!!,
                description = listOptions.value?.get(0)?.description!!,
                check = gluten
            )
            ItemFilter(
                title = listOptions.value?.get(1)?.title!!,
                description = listOptions.value?.get(1)?.description!!,
                check = lactose
            )
            ItemFilter(
                title = listOptions.value?.get(2)?.title!!,
                description = listOptions.value?.get(2)?.description!!,
                check = vegetarian
            )
            ItemFilter(
                title = listOptions.value?.get(3)?.title!!,
                description = listOptions.value?.get(3)?.description!!,
                check = vegan
            )

            Button(onClick = {
                viewModelMain.updateFilters(listOf(
                    FilterOptions("Gluten-free","Only include gluten-free meals", gluten.value!!),
                    FilterOptions("Lactose-free","Only include lactose-free meals",lactose.value!!),
                    FilterOptions("Vegetarian","Only include vegetarian meals", vegetarian.value!!),
                    FilterOptions("Vegan","Only include vegan meals",vegan.value!!,
                    )))
                        },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Colors.redPastel,

                ),
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(top = 10.dp)) {
                            Text("Save",
                                style = TextStyle(
                                    color = Colors.whitePastel,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                    }

        }
    }
}

