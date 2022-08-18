package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.category_body

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.HomeViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.CategoriesList
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun CategoryBody(
    viewModel : HomeViewModel = hiltViewModel()
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = LocalSpacing.current.medium,
                    end = LocalSpacing.current.small,
                    bottom = LocalSpacing.current.medium
                )
        ){
            Text("Find By Category",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primaryVariant,)
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text("See All",
                    style = TextStyle(
                        color = MaterialTheme.colors.secondary,
                        fontSize = 15.sp,)
                )
            }
        }
        if(viewModel.currentCategory.value !=null){
            CategoriesList(viewModel.listCategories.value,
                viewModel.currentCategory){
                viewModel.onEvent(HomeEvents.OnChangeCategory(it))
                viewModel.onEvent(HomeEvents.FilterRecipe)
            }
        }else{
            CircularProgressIndicator()
        }
    }
}