package com.example.weatherapp.feature_weather.presentation.screens.main.components.bottom_bar.components

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.weatherapp.feature_weather.domain.model.bottom_item.BottomItem

@Composable
fun RowScope.AddItem(
    screen: BottomItem,
    navController: NavController,
    currentDestination: NavDestination?
){
    BottomNavigationItem(
        label = { Text(screen.title) },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Icon of ${screen.title} page"
            )
        },
        onClick = {
            navController.navigate(screen.route)
            Log.d("Current", currentDestination.toString())
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.selected
        } == true,
        selectedContentColor = MaterialTheme.colors.primaryVariant,
        unselectedContentColor = MaterialTheme.colors.primary,
    )

}