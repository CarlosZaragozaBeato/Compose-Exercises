package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes

@Composable
fun DrawerContent(
    countryId:String,
    onNavigate:(String) -> Unit,
){
    Column {
       DrawerHeader()
        DrawerBody {
            if(it == Routes.WEEK_WEATHER.name || it == Routes.WEATHER.name || it == Routes.FAVORITES.name){
                onNavigate.invoke("$it/$countryId")
            }else{
                onNavigate.invoke(it)
            }
        }

    }
}