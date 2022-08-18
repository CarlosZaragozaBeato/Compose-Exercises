package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category.Category
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.category_body.CategoryItem
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun CategoriesList(
    list: List<Category>,
    currentCategory: MutableState<Category?>,
    onChangeCategory:(Category)-> Unit
){

    LazyRow(
        modifier = Modifier
            .height(150.dp)
            .padding(horizontal = LocalSpacing.current.small)
    ){
        items(list){item->
            CategoryItem(
                item = item,
                         selectedItem = currentCategory,
                        onChangeCategory =onChangeCategory)
        }

    }

}