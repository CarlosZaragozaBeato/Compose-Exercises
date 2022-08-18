package com.example.recipesapp.feature_recipe.presentation.screens.category_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recipesapp.feature_recipe.domain.model.recipe.LocalRecipe
import com.example.recipesapp.feature_recipe.presentation.util.Colors


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(
    item:LocalRecipe,
    onNavigation:()-> Unit = {}
){
           Card(
               elevation = 4.dp,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(330.dp)
                   .padding(horizontal = 15.dp, vertical = 20.dp),
               onClick = {onNavigation.invoke()}
           ){
               Image(painter = rememberAsyncImagePainter(model = item.image.toString()),
                   contentDescription = "Image of ${item.title}",
                   contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize())
               Column(
                   verticalArrangement = Arrangement.Bottom,
                   horizontalAlignment = Alignment.End
               ) {

                   Surface(modifier = Modifier
                       .fillMaxWidth(.7f)
                       .padding(end = 5.dp, bottom = 20.dp),
                     color =Color.Black.copy(.7f)
                   ){
                       Text(item.title,
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            color = Colors.whitePastel,
                            fontWeight = FontWeight.Bold
                        ),
                           modifier = Modifier
                               .padding(top = 5.dp, bottom = 5.dp,  start = 15.dp )
                       )
                   }
                   Row(
                       modifier = Modifier
                           .background(Colors.whitePastel)
                           .fillMaxWidth()
                           .height(50.dp),
                       horizontalArrangement = Arrangement.SpaceEvenly,
                       verticalAlignment = Alignment.CenterVertically
                   ) {

                       infoSection(title = "${item.duration} min", icon =Icons.Default.Timer)

                       infoSection(title = item.complexity.toString(), icon = Icons.Default.Badge)

                       infoSection(title = item.affordability.toString(), icon = Icons.Default.AttachMoney)

                   }
               }
           }
}

