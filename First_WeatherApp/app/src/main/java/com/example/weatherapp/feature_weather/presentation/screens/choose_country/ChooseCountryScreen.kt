package com.example.weatherapp.feature_weather.presentation.screens.choose_country

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.feature_weather.presentation.navigation.RouteScreens
import com.example.weatherapp.feature_weather.presentation.screens.choose_country.components.CardCountry
import com.example.weatherapp.feature_weather.presentation.utils.LoadingComponent

@Composable
fun ChooseCountryScreen(navController: NavController,
                        countryViewModel: CountryViewModel = hiltViewModel()
){

    val data = countryViewModel.countryData.value.data

    if(data?.data.isNullOrEmpty()){
        LoadingComponent()
    }else{

        Surface (color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxSize()){
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(text = "Select a Country".uppercase(),
                        style = MaterialTheme.typography.h4,

                color = MaterialTheme.colors.primary)
                LazyColumn {
                    items(data?.data?.size!!){ index ->

                        if(data.data[index].name != "Antarctica") {
                            CardCountry(country = data.data[index].name, capital = data.data[index].capital){
                                navController.navigate("${RouteScreens.DAY.name}/${data.data[index].name}")
                        }
                    }

                    }
                }
            }
        }

    }


}