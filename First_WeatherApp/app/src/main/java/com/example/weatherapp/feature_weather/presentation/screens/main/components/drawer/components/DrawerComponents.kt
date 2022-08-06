package com.example.weatherapp.feature_weather.presentation.screens.main.components.drawer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.feature_weather.domain.model.drawer_menu.MenuItem
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing

@Composable
fun DrawerHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary),

        )   {
        Text("Know Your Weather!!", fontSize = 30.sp,
            color = MaterialTheme.colors.surface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = LocalSpacing.current.large, horizontal = LocalSpacing.current.medium))
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(items[index])
                    }
                    .padding(vertical =  LocalSpacing.current.large),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = LocalSpacing.current.medium),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = items[index].icon,
                        contentDescription = items[index].contentDescription
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = items[index].title,
                        style = itemTextStyle,

                        )
                }

            }
        }
    }
}