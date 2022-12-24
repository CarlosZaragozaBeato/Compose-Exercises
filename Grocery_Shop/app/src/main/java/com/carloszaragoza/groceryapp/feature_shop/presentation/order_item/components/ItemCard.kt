package com.carloszaragoza.groceryapp.feature_shop.presentation.order_item.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

@Composable
fun ItemCard(
    item: Item,
    count:Map<String, Int>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RectangleShape,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onBackground.copy(.04f)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    contentScale = ContentScale.FillBounds
                )
            }
            Column {
                Text("Name: ${item.name}")
                Column {
                    Text(
                        "Quantity: ${
                            count[item.id].toString()
                        }"
                    )
                    Text(
                        "Total: ${
                            (count[item.id]?.toDouble()
                                ?.times(item.price))
                        }$"
                    )
                }
            }
        }
    }
}