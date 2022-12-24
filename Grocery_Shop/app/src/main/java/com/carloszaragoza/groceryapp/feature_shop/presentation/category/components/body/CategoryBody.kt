package com.carloszaragoza.groceryapp.feature_shop.presentation.category.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category

@Composable
fun CategoryBody(
    listOfItems: List<Item>,
    currentCategory: Category,
    addItem: (Item) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(Color(currentCategory.color).copy(.2f))
            .fillMaxHeight()
    ){
        items(listOfItems){ item->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                backgroundColor = Color.Transparent,
                shape = RectangleShape,
                elevation = 0.dp
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(.5f)
                            .padding(
                                horizontal = LocalSpacing.current.small,
                                vertical = LocalSpacing.current.medium
                            ),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = item.name,
                                style = MaterialTheme.typography.h4,
                                color = MaterialTheme.colors.onBackground.copy(.7f),
                                fontWeight = FontWeight.Bold,

                                )
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.onBackground,
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
                        IconButton(onClick = { addItem.invoke(item) }) {
                            Icon(imageVector = Icons.Default.AddShoppingCart,
                                contentDescription = "Add an Order")
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center){
                        Image(painter = painterResource(id = item.image),
                            contentDescription =item.name,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}