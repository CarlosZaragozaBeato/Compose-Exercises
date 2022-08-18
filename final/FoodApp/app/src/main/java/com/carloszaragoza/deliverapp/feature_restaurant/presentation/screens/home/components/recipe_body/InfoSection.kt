package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.recipe_body

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.starColor
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.ubicationColor

@Composable
fun InfoSection(
    rating:Double,
    ubication:String
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = LocalSpacing.current.small)

    ){
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.Star,
                contentDescription = "Star Icon",
                tint = starColor
            )
            Text(rating.toString(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant.copy(.5f)
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = ubicationColor
            )
            Text(ubication,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant.copy(.5f)
                )
            )
        }
    }
}