package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.orders.calculate_orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.components.DrawLineCustom
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.ubicationColor

@Composable
fun CalculateOrdersPrice(
    delivery:Double,
    total:Double,
    totalItems:Double,
    onDelete: () -> Unit
){

    Card(
        shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
        modifier = Modifier
            .fillMaxHeight()){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.small),

            verticalArrangement = Arrangement.SpaceBetween
        ) {
    Column() {
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
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            DrawLineCustom()
            Text(
                "$${String.format("%.2f", delivery)}",
                style = TextStyle(
                    color = ubicationColor,
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
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            DrawLineCustom()
            Text(
                "$${String.format("%.2f", totalItems)}",
                style = TextStyle(
                    color = ubicationColor,
                    fontWeight = FontWeight.Bold))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Button(
                    onClick = { onDelete.invoke() },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primaryVariant,
                        contentColor = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth(.5f)) {
                    Text("Pay ${String.format("%.2f",total)}")
                }
            }
        }



    }
}