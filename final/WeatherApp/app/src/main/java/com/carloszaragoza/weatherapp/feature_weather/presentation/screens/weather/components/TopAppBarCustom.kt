package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.AppBarTitle
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import kotlinx.coroutines.launch

@Composable
fun TopAppBarCustom(
    scaffoldState: ScaffoldState,
    CountryId:String,
    pop:() -> Unit,
    navigate: (String) -> Unit,
){
    val scope = rememberCoroutineScope()

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Default.Menu ,
                    contentDescription = "Menu Icon" )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ){
                AppBarTitle(title = CountryId,
                    color = MaterialTheme.colors.primaryVariant,
                    weight = FontWeight.Bold ,
                    size = MaterialTheme.typography.h5.fontSize)

                IconButton(onClick = { pop()}) {
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Drop Down")
                }
            }
        },
        actions =  {
            IconButton(onClick = {navigate(CountryId) }) {
                Icon(imageVector = Icons.Default.CalendarToday,
                    contentDescription = "Calendar")
            }
        },
        modifier = Modifier
            .padding(top = LocalSpacing.current.small)
    )
}