package com.carloszaragoza.groceryapp.feature_shop.presentation.category.components.app_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category

@Composable
fun CategoryAppBar(
    modifier: Modifier = Modifier,
    currentCategory:Category,
    onPop:()->Unit
) {
    TopAppBar(
        modifier = modifier
            .fillMaxHeight(.3f),
        backgroundColor = Color(currentCategory?.color!!),
        elevation = 0.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.5f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { onPop.invoke() }) {
                    Icon(imageVector = Icons.Default.ArrowBack ,
                        contentDescription ="Go to home Page",
                        tint = Color(0xff000000))
                }
                Text(currentCategory.name,
                    style = MaterialTheme.typography.h3,
                    color = Color(0xff000000).copy(0.8f),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.medium,
                            vertical = LocalSpacing.current.medium))
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
                Image(painter = painterResource(id = currentCategory?.bgImage),
                    contentDescription = currentCategory.name,
                    contentScale = ContentScale.Fit)
            }
        }
    }
}