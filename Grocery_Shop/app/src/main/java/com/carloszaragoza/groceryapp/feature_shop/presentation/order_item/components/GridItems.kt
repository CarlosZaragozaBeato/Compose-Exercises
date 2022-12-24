package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridItems(
    itemList:List<Item>,
    count:Map<String, Int>) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight(),
        cells = GridCells.Fixed(2)) {
        items(itemList) { item ->
            ItemCard(item = item,
                    count = count)
        }
    }
}