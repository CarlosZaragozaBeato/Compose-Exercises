package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing

@Composable
fun OrderItemInfo(
    currentId:Int,
    quantity: Int,
    total: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ){
        Text("Number: $currentId",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,)

        Column {
            Text("Total Items: $quantity",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold)

            Text("Total: $total",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold)
        }
    }
}