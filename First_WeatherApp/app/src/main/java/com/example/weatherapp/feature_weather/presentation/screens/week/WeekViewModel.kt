package com.example.weatherapp.feature_weather.presentation.screens.week

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.feature_weather.data.data_resource.DataOrException
import com.example.weatherapp.feature_weather.data.repository.WeatherRepository
import com.example.weatherapp.feature_weather.domain.model.seven_days.WeekTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {


    val weatherData: MutableState<DataOrException<WeekTime, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))


    fun searchWeather(query: String = "Spain") = viewModelScope.launch {
        if (query.isEmpty()) {
            return@launch
        }
        weatherData.value.loading = true
        weatherData.value = repository.getWeekWeather(query)
    }
}