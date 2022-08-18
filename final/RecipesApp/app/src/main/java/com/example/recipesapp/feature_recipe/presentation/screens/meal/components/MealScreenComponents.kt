package com.example.recipesapp.feature_recipe.presentation.screens.meal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.recipesapp.feature_recipe.domain.model.recipe.LocalRecipe
import com.example.recipesapp.feature_recipe.presentation.util.Colors

@Composable
fun MealScreenInfo(
    item: LocalRecipe
){
    Surface(
        color = Colors.lightBrownPastel.copy(.8f),
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 5.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(painter = rememberAsyncImagePainter(model = item.image),
                contentDescription = "Image of ${item.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp))
            TitleMeal("Ingredients")

            Surface(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(150.dp),
                color = Colors.whitePastel,
                border = BorderStroke(1.dp, Colors.blackPastel),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ){
                    items(item.ingredients.size){ index->
                        Card(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            backgroundColor = Colors.lightBrownPastel.copy(.8f)
                        ){
                            Text(item.ingredients[index],
                                modifier = Modifier
                                    .padding(5.dp))
                        }
                    }
                }

            }

            TitleMeal("Steps")

            Surface(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(150.dp),
                color = Colors.whitePastel,
                border = BorderStroke(1.dp, Colors.blackPastel),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ){
                    items(item.steps.size){ index->
                        Card(

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .fillMaxWidth()

                            ){
                                Card(
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .size(30.dp),
                                    backgroundColor = Colors.redPastel
                                ){
                                    Box(contentAlignment = Alignment.Center){
                                        Text(text = "#${index+1}",
                                            style= TextStyle(
                                                color = Colors.whitePastel
                                            )
                                        )
                                    }
                                }
                                Text(
                                    item.steps[index],
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2,
                                    modifier = Modifier
                                        .padding(start = 10.dp))

                            }
                        }

                        Divider()
                    }
                }

            }
        }
    }
}


@Composable
fun TitleMeal(title:String){
    Text(text = title,
        style = TextStyle(
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold,
            color = Colors.blackPastel.copy(.9f)
        ),
        modifier = Modifier
            .padding(vertical = 10.dp)
    )
}