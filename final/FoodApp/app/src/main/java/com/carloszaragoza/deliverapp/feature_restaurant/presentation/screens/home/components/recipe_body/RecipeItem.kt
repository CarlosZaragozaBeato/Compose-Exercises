package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.recipe_body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.recipe.Recipe
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun RecipeItem(
    item:Recipe,
    ubication:String,
    onAddOrder: (Order) -> Unit
){

        Column(
            modifier = Modifier
                .padding(end = LocalSpacing.current.medium)
                .width(200.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(elevation = 1.dp)
                .background(MaterialTheme.colors.primary.copy(.2f)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
          Box(
              modifier = Modifier
                  .fillMaxWidth()
                  .background(MaterialTheme.colors.primaryVariant),
              contentAlignment = Alignment.Center
          ){
              Image(
                  painter = painterResource(id = item.image),
                  contentDescription = "${item.name} image",
                  contentScale = ContentScale.Fit,
                  modifier = Modifier
                      .size(100.dp)
              )
          }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(item.name,
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                InfoSection(
                    rating = item.rating,
                    ubication = ubication
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = LocalSpacing.current.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("$${item.price}",
                    style = TextStyle(
                        color = MaterialTheme.colors.primaryVariant,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ))

                    IconButton(onClick = {
                                         onAddOrder.invoke(
                                             Order(
                                                 recipeId = item.id,
                                                 ubication = ubication
                                             )
                                         )
                    },
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStartPercent = 30))
                            .background(MaterialTheme.colors.primaryVariant)
                            ) {
                        Icon(imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping cart",
                            tint = MaterialTheme.colors.primary)
                    }
                }

        }
    }
    
