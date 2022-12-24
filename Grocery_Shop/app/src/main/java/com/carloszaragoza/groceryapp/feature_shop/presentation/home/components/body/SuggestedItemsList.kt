package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

@Composable
fun SuggestedItemsList(
    items: List<Item>,
    addItem:(Item)-> Unit,

) {
    
    Column {
        Text("Suggested Items",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = LocalSpacing.current.large,
                    top = LocalSpacing.current.large,
                    bottom = LocalSpacing.current.small)
        )
        LazyRow(
            modifier = Modifier
                .padding(LocalSpacing.current.small)
        ){
            items(items){ item->
                CardItem(
                    item=item
                ){
                    addItem(it)
                }
            }
        }
    }
    
}