package com.example.areader.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areader.data.DataOrException
import com.example.areader.model.MBook
import com.example.areader.repository.BookRepositoryv2
import com.example.areader.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryv2: FireRepository): ViewModel() {

    val data: MutableState<DataOrException<List<MBook>, Boolean, Exception>> = mutableStateOf(
        DataOrException(listOf(),true,Exception(""))
    )

    init{
        getAllBooksFromFirebase()
    }

     fun getAllBooksFromFirebase() {
        viewModelScope.launch {
            data.value.loading = true

            data.value = repositoryv2.getAllBooksFromDataBase()

            if(data.value.data?.isNotEmpty()!!){
                data.value.loading=false
            }
        }

    }


}