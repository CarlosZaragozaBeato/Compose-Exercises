package com.example.wheatherapp.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.model.Unit
import com.example.wheatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: WeatherDbRepository
): ViewModel() {

    private val _listUnits = MutableStateFlow<List<Unit>>(emptyList())
    val listUnits = _listUnits.asStateFlow()

    init {
         viewModelScope.launch {
             repository.getUnits().distinctUntilChanged().collect{
                 _listUnits.value = it

             }
         }
    }

     fun deleteAll() = viewModelScope.launch {
        repository.deleteAllUnit()
    }
    fun insertUnit(unit:Unit) = viewModelScope.launch {
        repository.insertUnit(unit)
    }


}