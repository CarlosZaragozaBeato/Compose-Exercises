package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.country.Data

@Composable
fun LazyItems(
    list:List<Data>,
    onNavigate: (String) -> Unit
){
    LazyColumn {
        items(list){item->
            if (item.capital != ""){
                CardCountry(
                   item = item
                ){
                    onNavigate.invoke(item.capital)
                }
            }
        }
    }
}