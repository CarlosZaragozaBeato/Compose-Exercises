package com.carloszaragoza.layoutexample.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarViewMonth
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomItem(
    val name:String,
    val icon:ImageVector,
    val route:String
){
    companion object{
        val list_bottom_items = listOf<BottomItem>(
            BottomItem(name = "HOME", icon = Icons.Default.Home, route ="HOME"),
            BottomItem(name = "CALENDAR", icon = Icons.Default.CalendarViewMonth, route ="CALENDAR"),
            BottomItem(name = "FAVORITES", icon = Icons.Default.FavoriteBorder, route ="FAVORITES"),
            BottomItem(name = "PROFILE", icon = Icons.Default.Person, route ="PROFILE")

        )
    }
}
