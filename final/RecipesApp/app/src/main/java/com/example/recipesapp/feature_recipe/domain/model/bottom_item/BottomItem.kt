package com.example.recipesapp.feature_recipe.domain.model.bottom_item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.recipesapp.feature_recipe.presentation.navigation.RouteScreen

sealed class BottomItem (
    val title:String,
    val route:String,
    val icon:ImageVector
        ){

    object  Home:BottomItem (
        route = RouteScreen.HOME.name,
        title = "Categories",
        icon = Icons.Default.Home
    )
    object  Favorites:BottomItem (
        route = RouteScreen.FAVORITES.name,
        title = "Favorites",
        icon = Icons.Default.Star
    )


}