package com.example.weatherapp.feature_weather.presentation.screens.main.components.drawer

import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.feature_weather.domain.model.drawer_menu.MenuItem
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.main.components.drawer.components.DrawerBody
import com.example.weatherapp.feature_weather.presentation.screens.main.components.drawer.components.DrawerHeader
import kotlinx.coroutines.launch

@Composable
fun DrawerContentCustom(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scope = rememberCoroutineScope()

    if (currentDestination?.hierarchy?.any {
            it.route == "${RouteScreens.DAY.name}/{country}"
                    ||
            it.route == "${RouteScreens.WEEK.name}/{country}"
        } == true) {

        DrawerHeader()

        DrawerBody(
            items = listOf(
                MenuItem(
                    id = "Country",
                    title = "Select Country",
                    contentDescription = "Go to \"Select Country\"",
                    icon = Icons.Default.Menu,
                    route = RouteScreens.CHOOSE_COUNTRY.name
                ),
                MenuItem(
                    id = "Day",
                    title = "View the Weather",
                    contentDescription = "Go to the weather screen screen",
                    icon = Icons.Default.Cloud,
                    route = "${RouteScreens.DAY.name}/${navBackStackEntry?.arguments?.getString("country")}"
                ),
                MenuItem(
                    id = "Favorites",
                    title = "Favorites",
                    contentDescription = "Go to the Favorites screen",
                    icon = Icons.Default.Favorite,
                    route = RouteScreens.FAVORITES.name
                )
            ),
            onItemClick = {
                navController.navigate(it.route)
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        )
    }
}
