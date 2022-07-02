package com.example.areader.repository

import com.example.areader.data.DataOrException
import com.example.areader.model.MBook
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryBook: Query
) {

    suspend fun getAllBooksFromDataBase(): DataOrException<List<MBook>,Boolean,Exception>{
        val DataOrException = DataOrException<List<MBook>,Boolean,Exception>()

        try{

            DataOrException.loading = true
            DataOrException.data = queryBook.get().await().documents.map { documents->
                documents.toObject(MBook::class.java)!!
            }

            if(DataOrException.data?.isNullOrEmpty() == false){
                DataOrException.loading = false
            }


        }catch (ex:Exception){
            DataOrException.e = ex
        }
        return DataOrException
    }
}