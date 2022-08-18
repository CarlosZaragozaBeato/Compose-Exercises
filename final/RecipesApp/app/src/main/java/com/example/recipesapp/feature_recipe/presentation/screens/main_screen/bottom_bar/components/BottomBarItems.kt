package com.example.recipesapp.feature_recipe.presentation.screens.main_screen.bottom_bar.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.example.recipesapp.feature_recipe.domain.model.bottom_item.BottomItem
import com.example.recipesapp.feature_recipe.presentation.util.Colors

@Composable
fun RowScope.AddItem(
    screen: BottomItem,
    navController: NavHostController,
    currentDestination: NavDestination?
){
    BottomNavigationItem(
        label = { Text(screen.title) },
        icon = { Icon(imageVector = screen.icon,
            contentDescription = "Icon of ${screen.title} page")
        },
        onClick = {
            navController.navigate(screen.route)
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        selectedContentColor = Colors.lightBrownPastel,
        unselectedContentColor = Colors.greyPastel)

}

