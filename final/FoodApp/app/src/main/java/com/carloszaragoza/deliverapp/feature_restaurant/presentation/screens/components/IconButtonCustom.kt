package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents

@Composable
fun IconButtonCustom(
    action:()-> Unit,
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(50.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primaryVariant.copy(.1f),
                shape = RoundedCornerShape(10.dp)
            ),
    ){
        IconButton(onClick = { action.invoke() },) {
            Icon(imageVector = icon ,
                contentDescription = description,
                tint = MaterialTheme.colors.primaryVariant.copy(.8f),
                modifier = Modifier.size(30.dp))
        }
    }
}