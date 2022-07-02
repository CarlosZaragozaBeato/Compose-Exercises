package com.example.areader.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.areader.data.Resource
import com.example.areader.model.Item

import com.example.areader.repository.BookRepositoryv2
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: BookRepositoryv2
): ViewModel(){


    suspend fun getBookInfo(bookId:String): Resource<Item> {


            return repository.getBookInfo(bookId)
    }

}