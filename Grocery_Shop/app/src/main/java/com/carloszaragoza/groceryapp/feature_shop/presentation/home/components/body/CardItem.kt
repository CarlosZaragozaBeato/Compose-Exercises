package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
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
fun CardItem(
    item: Item,
    addItem: (Item)->Unit
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(170.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary.copy(.3f),
        shape = RectangleShape

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.4f),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop)
            }
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Column (
                    modifier = Modifier
                        .padding(LocalSpacing.current.small)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Top){
                    Text(text = item.name,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground)
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
                Box(modifier = Modifier
                    .fillMaxHeight(),
                    contentAlignment = Alignment.BottomEnd){
                    IconButton(onClick = { addItem.invoke(item) }) {
                        Icon(imageVector = Icons.Default.AddShoppingCart,
                            contentDescription = "Add Order")
                    }
                }
            }

        }
    }
}