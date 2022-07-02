package com.example.areader.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.Resource
import com.example.areader.model.Item
import com.example.areader.repository.BookRepositoryv2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModelv2 @Inject constructor(
    private val repository: BookRepositoryv2
): ViewModel() {

    var list: List<Item> by mutableStateOf(listOf())
    var isLoading:Boolean  by mutableStateOf(true)

    init{
        loadBooks()
    }

     fun loadBooks() {
       searchBooks("android")
    }

     fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default){



            if(query.isEmpty()){
                return@launch
            }

            try{

                when(val response = repository.getBooks(query)){

                    is Resource.Success -> {
                        list = response.data!!
                        if(list.isNotEmpty()){
                            isLoading = false
                        }
                    }
                    is Resource.Error ->{
                        isLoading = false
                        Log.d("EROOR", "Error")
                    }
                    else ->{

                    }

                }

            }catch(ex:Exception){
                isLoading = false
                Log.d("ERROR V2", "${ex.message}")
            }

        }
    }
}