package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.weatherapp.feature_weather.presentation.navigation.Routes
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.search_country.util.SearchCountryEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import com.carloszaragoza.weatherapp.feature_weather.data.data_resource.DataOrException
import com.carloszaragoza.weatherapp.feature_weather.data.repository.CountryRepository
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.country.Country
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.country.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCountryViewModel @Inject constructor(
    private val repository: CountryRepository
):ViewModel() {

    val countryData =   mutableStateOf<DataOrException<Country, Boolean, Exception>>(DataOrException(null, true, Exception("")))

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val searchByTitle =  mutableStateOf("")

    var listFiltered = mutableStateOf<List<Data>?>(emptyList())

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


    fun onEvent(event: SearchCountryEvents){
        when(event){
            is SearchCountryEvents.onChangeCountryFilter -> {
                searchByTitle.value = event.filter
                listFiltered.value = countryData.value.data?.data?.filter{ country ->
                    country.name.uppercase().contains(event.filter.uppercase())
                }!!
            }
            is SearchCountryEvents.onNavigate -> {
                sendUiEvent(event = UiEvent.onNavigate("${Routes.WEATHER.name}/${event.id}"))
            }
        }
    }

    private fun sendUiEvent(event:UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}