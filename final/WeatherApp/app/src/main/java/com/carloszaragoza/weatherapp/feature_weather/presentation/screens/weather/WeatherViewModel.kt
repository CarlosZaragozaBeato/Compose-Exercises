package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.weather.util.WeatherEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import com.carloszaragoza.weatherapp.feature_weather.data.data_resource.DataOrException
import com.carloszaragoza.weatherapp.feature_weather.data.repository.WeatherRepository
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    savedStatedHandle:SavedStateHandle,
    private val repository: WeatherRepository
):ViewModel(){


    var countryId = ""
    private val _weatherData: MutableStateFlow<DataOrException<WeekTime, Boolean, Exception>> =
        MutableStateFlow(DataOrException(null, true, Exception("")))

    val weatherData = _weatherData.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        countryId = savedStatedHandle.get<Int>("country").toString()
        searchWeather(countryId)
    }

    fun onEvent(event:WeatherEvents){
        when(event){
            is WeatherEvents.onPop ->{
                sendUiEvent(UiEvent.popBackStack)
            }
            is WeatherEvents.onNavigate ->{
                sendUiEvent(event = UiEvent.onNavigate(event.id))
            }

        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

     private fun searchWeather(query: String = "Spain") = viewModelScope.launch {
        if (query.isEmpty()) {
            return@launch
        }
        _weatherData.value.loading = true
        _weatherData.value = repository.getWeekWeather(query)
    }
}