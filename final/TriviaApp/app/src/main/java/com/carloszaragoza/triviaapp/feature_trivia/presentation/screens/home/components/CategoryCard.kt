package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.category_model.Category

@Composable
fun CategoryCard(
    category:Category,
    index:Int,
    onNavigate:(String)->Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 20.dp,vertical = 30.dp)
            .clickable {
                onNavigate(category.id)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(brush = Brush.linearGradient(category.gradient))
                .padding(10.dp)
                .fillMaxSize()
        ){
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.6f)
            ) {
                Text("Category #${index + 1}",
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.caption.fontSize,
                        color = Color.LightGray),

                )
                Text(text = category.title,
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }

            Image(
                painter = painterResource(id = category.image),
                contentDescription = "Category Image",
                modifier = Modifier

                    .offset(x = 0.dp, y=(-50).dp)
                    .clip(RoundedCornerShape(CornerSize(20.dp))),
                contentScale = ContentScale.Fit

            )
        }
    }
}