package com.example.weatherapp.feature_weather.presentation.screens.choose_country

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.feature_weather.data.data_resource.DataOrException
import com.example.weatherapp.feature_weather.data.repository.CountryRepository
import com.example.weatherapp.feature_weather.domain.model.country.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
):ViewModel(){

    val countryData: MutableState<DataOrException<Country, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))


    init{
        searchCountry()
    }
    private fun searchCountry() = viewModelScope.launch {
        countryData.value.loading = true
        countryData.value = repository.getCountry()

        if (countryData.value.data.toString().isNotEmpty()) {
            countryData.value.loading = false
        }
    }
}