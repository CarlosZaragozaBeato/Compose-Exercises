package com.example.wheatherapp.screens.main

import androidx.lifecycle.ViewModel
import com.example.wheatherapp.data.DataOrException
import com.example.wheatherapp.model.Weather
import com.example.wheatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository:WeatherRepository
):ViewModel (){

    suspend fun getWeatherData(city: String, units: String):DataOrException<
            Weather,
            Boolean,
            Exception>{
        return repository.getWeather(city = city,
                                unit = units)
    }
}