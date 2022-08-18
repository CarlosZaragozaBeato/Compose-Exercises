package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.bottom_navigation_class

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.navigation.Routes

class BottomItem (
    val route:String,
    val icon:ImageVector){

     companion object{
         val listOfItems = listOf(
             BottomItem(
                 route = Routes.HOME.name,
                 icon = Icons.Default.Home
             ),
             BottomItem(
                 route = Routes.ORDERS.name,
                 icon = Icons.Default.Person
             ),
         )
     }
}