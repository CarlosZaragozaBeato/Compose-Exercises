package com.carloszaragoza.groceryapp.feature_shop.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category

@Composable
fun CardCategory(
    item:Category,
    onNavigate: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(170.dp)
            .clickable{
                      onNavigate.invoke(item.id)
            },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.primary.copy(.3f),
        shape = RectangleShape

    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = LocalSpacing.current.medium)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f),
                contentAlignment = Alignment.Center
            ){
                Image(painter = painterResource(id = item.image),
                    contentDescription = item.name,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds)
            }
            Text(text = item.name,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold)

        }
        }
}