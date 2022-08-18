package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing

@Composable
fun TextLocation(
    city:String,
    country:String
){
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(start = LocalSpacing.current.small),
    ){
        Icon(imageVector = Icons.Filled.LocationOn,
            tint = MaterialTheme.colors.secondary,
            contentDescription = "Compass Icon")
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 15.sp,
                                fontWeight = FontWeight.Bold)) {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primaryVariant
                        )
                    ) {
                        append(city)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primaryVariant.copy(.8f)
                        )
                    ) {
                        append(", $country",)
                    }
                }
            })
    }
}