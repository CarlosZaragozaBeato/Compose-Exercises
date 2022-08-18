package com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.drawer_item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes

class DrawerItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector,
    val route: String
){
    companion object {
        val listOfItems = listOf(
            DrawerItem(id = "d1",
            title = "Today Weather",
            contentDescription = "Current Weather",
            icon = Icons.Default.Today,
            route = Routes.WEATHER.name),
            DrawerItem(id = "d2",
                title = "Week Weather",
                contentDescription = "Week Weather",
                icon = Icons.Default.Cloud,
                route = Routes.WEEK_WEATHER.name),
            DrawerItem(id = "d3",
                title = "Country Search",
                contentDescription = "Country Search",
                icon = Icons.Default.Map,
                route = Routes.COUNTRY.name),
            DrawerItem(id = "d4",
                title = "Favorites",
                contentDescription = "Favorites Icon",
                icon = Icons.Default.Favorite,
                route = Routes.FAVORITES.name)
        )
    }
}



