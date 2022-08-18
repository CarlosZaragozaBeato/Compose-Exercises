package com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.favorites.Favorites
import com.carloszaragoza.weatherapp.feature_weather.presentation.screens.week_time.utils.WeekTimeEvents
import com.carloszaragoza.weatherapp.feature_weather.presentation.util.UiEvent
import com.carloszaragoza.weatherapp.feature_weather.data.data_resource.DataOrException
import com.carloszaragoza.weatherapp.feature_weather.data.repository.WeatherRepository
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime
import com.carloszaragoza.weatherapp.feature_weather.domain.use_cases.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeekWeatherViewModel @Inject constructor(
    savedStatedHandle: SavedStateHandle,
    private val repository: WeatherRepository,
    private val useCases: FavoritesUseCases
): ViewModel() {


     val weatherData: MutableState<DataOrException<WeekTime, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

    private val _favorite = MutableStateFlow<Favorites?>(null)
    val favorite = _favorite.asStateFlow()

    val isFavorite = mutableStateOf<Boolean?>(null)

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var countryId = ""

    init{
        countryId = savedStatedHandle.get<Int>("week").toString()
        searchWeather(countryId)
        getFavorite(countryId)
    }

    fun onEvent(event: WeekTimeEvents){
        when(event){
            is WeekTimeEvents.onPop ->{
                sendUiEvent(UiEvent.popBackStack)
            }
            is WeekTimeEvents.onAddFavorite -> {
                viewModelScope.launch(Dispatchers.IO){
                    useCases.addFavorite(event.favorite)
                }
                isFavorite.value = true
            }
            is WeekTimeEvents.onDeleteFavorite -> {
                viewModelScope.launch(Dispatchers.IO){
                    useCases.deleteFavorite(event.favorite)
                }
                isFavorite.value = false
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getFavorite(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            _favorite.value =  useCases.getFavoriteById(id)
            isFavorite.value = favorite.value?.title == countryId
        }

    }

    private fun searchWeather(query: String = "Spain") = viewModelScope.launch {
        if (query.isEmpty()) {
            return@launch
        }
        weatherData.value.loading = true
        weatherData.value = repository.getWeekWeather(query)
    }
}