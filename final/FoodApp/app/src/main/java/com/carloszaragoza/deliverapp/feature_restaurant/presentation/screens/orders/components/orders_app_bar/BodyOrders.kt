package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.components.orders_app_bar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.recipe_body.InfoSection
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.OrdersViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun BodyOrders(
    viewModel: OrdersViewModel = hiltViewModel()
){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height((screenHeight/1.5).dp)
            .padding(
                horizontal = LocalSpacing.current.medium,
                vertical = LocalSpacing.current.small
            )
    ){
        items(viewModel.listOfOrders.value){ item->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(vertical = LocalSpacing.current.medium),
                elevation = 0.dp,
                border = BorderStroke(width = 1.dp,
                    color = MaterialTheme.colors.primaryVariant.copy(.25f)),
                backgroundColor = MaterialTheme.colors.primary.copy(.5f)
            ){
                Row(){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(.3f)
                            .fillMaxHeight()

                            .background(MaterialTheme.colors.primaryVariant),
                        contentAlignment = Alignment.BottomCenter
                    ){
                        Image(painter = painterResource(id = item.image),
                            contentDescription = item.name,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary.copy(.05f))
                                .fillMaxWidth()
                                .fillMaxHeight(.6f)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = LocalSpacing.current.small)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ){

                        Column(
                            modifier = Modifier
                                .padding(top = LocalSpacing.current.medium)
                        ) {
                            Text(item.name)

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(.5f)
                            ){
                                InfoSection(rating = item.rating ,
                                    ubication = "400m")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(end = LocalSpacing.current.medium),
                            horizontalArrangement = Arrangement.SpaceBetween,

                            ){
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text("$${item.price}",
                                    style = TextStyle(
                                        color = MaterialTheme.colors.primaryVariant,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.Bottom,

                                ) {
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                topStartPercent = 10,
                                                topEndPercent = 10
                                            )
                                        )
                                        .background(MaterialTheme.colors.primaryVariant)
                                        .width(30.dp)
                                        .fillMaxHeight(.7f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        viewModel.onEvent(OrdersEvents.OnDeleteItem(item.id))
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Remove,
                                            contentDescription = "Remove",
                                            tint = MaterialTheme.colors.primary
                                        )
                                    }
                                }
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .padding(horizontal = LocalSpacing.current.medium)
                                        .fillMaxHeight(.5f),
                                ){
                                    Text(viewModel.countOrders.value.get(item.id).toString(),
                                    style = TextStyle(
                                        color = MaterialTheme.colors.primaryVariant,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 17.sp))
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(
                                            RoundedCornerShape(
                                                topStartPercent = 10,
                                                topEndPercent = 10
                                            )
                                        )
                                        .background(MaterialTheme.colors.primaryVariant)
                                        .width(30.dp)
                                        .fillMaxHeight(.7f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        viewModel.onEvent(OrdersEvents.OnAddItem(
                                                Order(
                                                    ubication = "400m",
                                                    recipeId = item.id
                                                )
                                        ))
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Add",
                                            tint = MaterialTheme.colors.primary
                                        )
                                    }
                                }
                            }
                        }


                    }
                }


            }

        }
    }
}