package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.favorites.util.FavoritesEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.LoadingComponent
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.components.DrawerContent
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onNavigate: (UiEvent.onNavigate) -> Unit,
    onPop: () -> Unit
){
    LaunchedEffect(key1 = true ){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvent.onNavigate -> onNavigate(event)
                is UiEvent.popBackStack -> onPop()
            }
        }
    }

    if(viewModel.listOfFavorites.value == null) {
        LoadingComponent()
    }else if(viewModel.listOfFavorites.value?.isEmpty()!!){
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    navigationIcon = {
                                     IconButton(onClick = { viewModel.onEvent(FavoritesEvents.OnPop) }) {
                                         Icon(imageVector = Icons.Default.ArrowBack,
                                             contentDescription = "Arrow Back")
                                     }
                    },
                    title = {
                        Text("Favorites")
                    }
                )
            }
        ){
        Box(modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {
            Text(
                "No Favorites...",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    color = MaterialTheme.colors.primaryVariant,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        }
    }

    else {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = { DrawerContent(countryId = viewModel.countryId,
                onNavigate = {
                    viewModel.onEvent(FavoritesEvents.OnNavigate(it))
                } )},
            topBar = {
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
                    title = {Text("Favorites")}
                )
            }
        ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(viewModel.listOfFavorites.value!!){ item ->
                        Card(
                            elevation = 0.dp,
                            modifier = Modifier
                                .padding(LocalSpacing.current.medium)
                                .clickable {
                                    viewModel.onEvent(FavoritesEvents.OnNavigate(Routes.WEATHER.name + "/${item.title}"))
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(LocalSpacing.current.medium)
                            ){
                                Text(item.title,
                                    style = TextStyle(
                                        color = MaterialTheme.colors.primaryVariant,
                                        fontSize = MaterialTheme.typography.h6.fontSize),
                                    modifier = Modifier
                                        .padding(LocalSpacing.current.medium)
                                )
                                Icon(imageVector = Icons.Default.Map,
                                    contentDescription = "Map Icon",
                                    tint = MaterialTheme.colors.secondary)
                            }
                        }
                        Divider(
                            modifier = Modifier.fillMaxWidth(.9f)
                        )
                    }
                }
        }
    }

}