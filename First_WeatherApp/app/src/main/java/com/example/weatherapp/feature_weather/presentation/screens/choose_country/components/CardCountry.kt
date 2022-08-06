package com.example.weatherapp.feature_weather.presentation.screens.choose_country.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun CardCountry(
    country:String = "Spain",
    capital:String = "Madrid",
    onNavigate: () -> Unit = {}
){


        Card(
            modifier = Modifier
                .clickable { onNavigate.invoke() }
                .fillMaxWidth(.8f)
                .padding(vertical = 10.dp)
        ) {

         Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)){
             Column(
                 modifier = Modifier
             ){
                 Text(text = country,
                     style = MaterialTheme.typography.h6,
                     color = MaterialTheme.colors.primary)
                 Text(text = capital,
                     style = MaterialTheme.typography.body1
                     ,color = MaterialTheme.colors.primaryVariant)
             }
             Icon(imageVector = Icons.Default.Menu,
                 contentDescription = "Menu Icon")
         }

        }


}