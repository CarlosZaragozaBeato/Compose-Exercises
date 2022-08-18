package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.components.CustomTextField
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.components.LazyItems
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.util.SearchCountryEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.AppBarTitle
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.util.LoadingComponent
import com.carloszaragoza.weatherapp.feature_weather.presentation.theme.LocalSpacing
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchCountryScreen(
    onNavigate:(UiEvent.onNavigate) -> Unit,
    viewModel: SearchCountryViewModel = hiltViewModel()
){

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.onNavigate -> onNavigate(event)
                else -> Unit
            }
        }
    }


    if(viewModel.countryData.value.data?.data.isNullOrEmpty()){
        LoadingComponent()
    }else {
        Scaffold(
            topBar = {
                if (viewModel.countryData.value.data != null) {
                    TopAppBar(
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp,
                        title = {
                            Column {
                                AppBarTitle(
                                    title = "Search",
                                    size = MaterialTheme.typography.h6.fontSize,
                                    color = MaterialTheme.colors.primaryVariant,
                                    weight = FontWeight.Bold
                                )
                                AppBarTitle(
                                    title = "Country",
                                    size = MaterialTheme.typography.h6.fontSize,
                                    color = MaterialTheme.colors.primaryVariant,
                                    weight = FontWeight.Bold
                                )
                            }
                        },
                        actions = {
                            CustomTextField(text = viewModel.searchByTitle.value,
                                onValueChange = {
                                    viewModel.onEvent(SearchCountryEvents.onChangeCountryFilter(it))
                                },
                                placeholder = "Search Country",
                                onDone = {})
                        },
                        modifier = Modifier
                            .padding(
                                vertical = LocalSpacing.current.medium,
                                horizontal = LocalSpacing.current.small
                            )
                    )
                }
            }
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( LocalSpacing.current.medium)
            )
            {
                if (viewModel.listFiltered.value.isNullOrEmpty()){
                    LazyItems(
                        list = viewModel.countryData.value.data?.data!!,
                        onNavigate = {
                            viewModel.onEvent(SearchCountryEvents.onNavigate(it))
                        })
                } else {
                    LazyItems(
                        list = viewModel.listFiltered.value!!,
                        onNavigate = {
                            viewModel.onEvent(SearchCountryEvents.onNavigate(it))
                        }
                    )
                }
            }
        }
    }
}
