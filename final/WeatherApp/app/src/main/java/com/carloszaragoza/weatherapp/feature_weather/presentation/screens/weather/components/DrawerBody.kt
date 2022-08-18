package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.drawer_item.DrawerItem
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing

@Composable
fun DrawerBody(
    onNavigate:(String) -> Unit
){
    LazyColumn {
        items(DrawerItem.listOfItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNavigate.invoke(item.route)
                    }
                    .padding(vertical = LocalSpacing.current.large),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = LocalSpacing.current.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.contentDescription
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.title)

                }

            }
        }
    }
}