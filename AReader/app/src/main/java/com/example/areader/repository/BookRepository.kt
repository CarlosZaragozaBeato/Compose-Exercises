package com.example.areader.repository

import android.provider.ContactsContract
import com.example.areader.data.DataOrException
import com.example.areader.model.Book
import com.example.areader.model.Item
import com.example.areader.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookApi: BooksApi
) {
    private val dataOrException = DataOrException<List<Item>,Boolean,Exception>()
    private val dataOrExceptionBook = DataOrException<Item,Boolean,Exception>()

    suspend fun getBooks(searchQuery:String): DataOrException<List<Item>,Boolean,Exception>{



        try{
            dataOrException.loading = true
            dataOrException.data = bookApi.getAllBooks(searchQuery).items

            if(dataOrException.data!!.isNotEmpty()){
                dataOrException.loading = false
            }

        }catch (ex:Exception){
        dataOrException.e = ex
        }
        return dataOrException


    }

   suspend fun getBookInfo(bookId:String):DataOrException<Item,Boolean,Exception>{

    val response = try {

        dataOrException.loading = true
        dataOrExceptionBook.data = bookApi.getBookInfo(bookId)

        if(dataOrExceptionBook.data.toString().isNotEmpty()){
            dataOrExceptionBook.loading = false
        }else{}

    }catch (ex : Exception){
        dataOrExceptionBook.e = ex
    }

       return dataOrExceptionBook

   }

}