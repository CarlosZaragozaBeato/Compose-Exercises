package com.example.weatherapp.feature_weather.domain.model.bottom_item

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.day.DayViewModel


 class BottomItem(
     val title: String,
     val route: String,
     val icon: ImageVector,
     val selected: String
 )