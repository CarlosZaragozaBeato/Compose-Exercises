package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.home_app_bar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.navigation.Routes
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.components.IconButtonCustom
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.utils.OrdersEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.UiEvent

@Composable
fun TitleIcons(
    numberOfOrders: Int? = null,
    navigate:(String) -> Unit
){
    Row(
        modifier = Modifier
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {

        IconButtonCustom(
            icon = Icons.Default.Search,
            modifier = Modifier,
            description = "Search Icon",
            action = {})
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.primaryVariant.copy(.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                ,
        ) {
            if (numberOfOrders != null && numberOfOrders > 0) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .offset(y = (-18).dp, x = 10.dp)
                        .size(20.dp)
                        .clip(CircleShape)
                        .shadow(elevation = 2.dp)
                        .zIndex(2f)
                        .background(MaterialTheme.colors.onError)

                ) {
                    Text(
                        numberOfOrders.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                    )
                }

            }
            IconButton(onClick = {
                navigate.invoke(Routes.ORDERS.name)
            }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Shop cart Icon",
                    modifier = Modifier.size(30.dp),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }

        }

    }
