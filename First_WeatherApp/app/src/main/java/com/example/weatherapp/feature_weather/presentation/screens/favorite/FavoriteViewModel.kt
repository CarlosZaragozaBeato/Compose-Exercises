package com.example.weatherapp.feature_weather.presentation.screens.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.feature_weather.domain.model.favorites.Favorites
import com.example.weatherapp.feature_weather.domain.use_case.FavoritesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: FavoritesUseCases
):ViewModel() {


    val listOfFavorites = mutableStateOf<List<Favorites>?>(null)
    private val _favorite = MutableStateFlow<Favorites?>(null)
    val favorite = _favorite.asStateFlow()

     fun addFavorite(favorite:Favorites)= viewModelScope.launch(Dispatchers.IO){
         useCase.addFavorite(favorite)
    }

     fun removeFavorite(favorite:Favorites) = viewModelScope.launch(Dispatchers.IO) {
         useCase.deleteFavorite(favorite)
    }

    fun getFavorites()= viewModelScope.launch {
        useCase.getFavorites().distinctUntilChanged().collect{
            listOfFavorites.value = it
        }
    }

    fun getFavoriteById(id:String)=
        viewModelScope.launch (Dispatchers.IO){
            _favorite.value =  useCase.getFavoriteById(id)
        }


}