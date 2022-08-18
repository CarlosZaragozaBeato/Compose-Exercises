package com.example.recipesapp.feature_recipe.presentation.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipesapp.feature_recipe.domain.model.category.Category
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardHome(
    category: Category,
    navController: NavController
){
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(15.dp)
            .clip(shape = RoundedCornerShape(CornerSize(5.dp)),)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        category.color.copy(alpha = 0.7f),
                        category.color
                    )
                )
            ),
        onClick = {
            navController.navigate("${RouteScreen.DETAILS.name}/${category.title}/${category.id}")
        },
        backgroundColor = Color.Transparent,
    ){
             Text(category.title,
                 style = TextStyle(
                     fontWeight = FontWeight.Bold,
                     fontSize = 20.0.sp,
                     color = Colors.blackPastel,),
                 modifier = Modifier
                     .padding(10.dp)
             )

    }

}