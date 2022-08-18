package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.category_body

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category.Category
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing


@Composable
fun CategoryItem(
    item:Category,
    selectedItem: MutableState<Category?>,
    onChangeCategory:(Category) -> Unit
){

    Card(
        modifier = Modifier
            .padding(horizontal = LocalSpacing.current.small)
            .clickable{
                onChangeCategory(item)
            }
            .width(80.dp)
            .height(100.dp),
        shape =  RoundedCornerShape(10.dp),
        border= BorderStroke(
                width = 2.dp,
                color =
                if(selectedItem.value == item)
                MaterialTheme.colors.secondary
                else
                Color.Transparent,)
    ){
       Column(
           modifier = Modifier
               .padding(LocalSpacing.current.small),
           verticalArrangement = Arrangement.SpaceBetween,
           horizontalAlignment = Alignment.CenterHorizontally
       ){
           Image(painter = painterResource(id = item.image),
               contentDescription = "${item.name} image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth())

           Text(item.name,
            style = TextStyle(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold
            )
           )
       }
    }
}