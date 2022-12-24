package com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing

@Composable
fun ItemCard(
    item: Item,
    quantity:Int,
    onDelete:(String) -> Unit,
    onAddItem:(Item) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RectangleShape,
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onBackground.copy(.04f)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),
            ){
                Image(painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    contentScale = ContentScale.FillBounds)
            }

            Column(
                modifier = Modifier.padding(LocalSpacing.current.small),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column{
                    Text(item.name,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(.5f),
                                    fontSize = MaterialTheme.typography.body1.fontSize,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                withStyle(style = SpanStyle()){
                                    append("${item.weight}kg")
                                }
                                withStyle(style = SpanStyle()){
                                    append(" / ")
                                }
                                withStyle(style = SpanStyle()){
                                    append("${item.price}$")
                                }
                            }
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = { onDelete.invoke(item.id) }) {
                        Icon(imageVector = Icons.Default.Remove,
                            contentDescription = "Remove Item")
                    }

                    Text(quantity.toString())

                    IconButton(onClick = { onAddItem.invoke(item) }) {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "Add Item")
                    }
            }
            }
        }
    }

}