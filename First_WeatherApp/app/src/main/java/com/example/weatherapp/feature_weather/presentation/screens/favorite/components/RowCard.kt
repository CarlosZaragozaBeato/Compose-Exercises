package com.example.weatherapp.feature_weather.presentation.screens.favorite.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing

@Composable
fun RowCard(favorite: Favorites,
            navController: NavController
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable{
                navController.navigate("${RouteScreens.DAY.name}/${favorite.title}")
            },
        verticalAlignment = Alignment.CenterVertically,


        ){
        Icon(imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite Icon",
            modifier = Modifier
                .padding(start = LocalSpacing.current.large ))

        Text(text = favorite.title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(start = LocalSpacing.current.small))
    }
}