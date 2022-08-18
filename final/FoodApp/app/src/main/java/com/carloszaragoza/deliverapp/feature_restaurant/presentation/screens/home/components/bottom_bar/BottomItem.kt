package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.HomeViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.bottom_navigation_class.BottomItem

@Composable
fun BottomItems(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary.copy(.95f),
        elevation = 1.dp
    ) {
        BottomItem.listOfItems.forEach{ item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { route ->
                    route.route == item.route
                } == true,
                onClick = {
                    viewModel.onEvent(HomeEvents.onNavigate(item.route))
                }, icon = {
                    Icon(imageVector = item.icon,
                        contentDescription = "Icon")
                },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor =  MaterialTheme.colors.primaryVariant.copy(.1f),
                label = {
                    if(currentDestination?.hierarchy?.any { route ->
                            route.route == item.route
                        } == true){

                        Box(modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.primaryVariant))
                    }else{
                        Text("")
                    }
                }

            )


        }
    }
}