package com.example.recipesapp.feature_recipe.presentation.screens.category_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun infoSection(
    title:String,
    icon: ImageVector
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            icon,
            contentDescription = " $title icon"
        )
        Text( title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.caption.fontSize,),
            modifier = Modifier
                .padding(start = 5.dp)
        )
    }
}