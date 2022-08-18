package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.country.Data

@Composable
fun CardCountry(
    item: Data,
    onNavigate: () -> Unit = {}
){
    Card(
        modifier = Modifier
            .clickable { onNavigate.invoke() }
            .fillMaxWidth()
            .padding(vertical = LocalSpacing.current.small)
    ) {

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSpacing.current.medium)){
            Column(
                modifier = Modifier
            ){
                Text(text = item.name,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.secondary)
                Text(text = item.capital,
                    style = MaterialTheme.typography.body1
                    ,color = MaterialTheme.colors.primaryVariant)
            }
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = "Menu Icon")
        }

    }


}