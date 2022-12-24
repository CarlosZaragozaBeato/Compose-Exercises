package com.carloszaragoza.groceryapp.feature_shop.presentation.orders.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing


    @Composable
    fun OrderInfo(
        delivery:Double,
        total:Double,
        totalItems:Double,
        onComplete: () -> Unit
    ){

        Card(
            shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
            modifier = Modifier
                .fillMaxHeight(.5f),
            backgroundColor = Color(0xff2C3333)){
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = LocalSpacing.current.large,
                            end = LocalSpacing.current.small,
                            start = LocalSpacing.current.small,
                            bottom = LocalSpacing.current.medium),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpacing.current.small)
                    ) {
                        Text(
                            "Delivery",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        DrawLineCustom()
                        Text(
                            "$${String.format("%.2f", delivery)}",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpacing.current.small)
                    ) {
                        Text(
                            "Total Order",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                        DrawLineCustom()
                        Text(
                            "$${String.format("%.2f", totalItems)}",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold))
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        onClick = { onComplete.invoke() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color(0xff2C3333)),
                        modifier = Modifier
                            .fillMaxWidth(.5f)) {
                        Text("Pay ${String.format("%.2f",total)}")
                    }
                }
            }



        }
    }


