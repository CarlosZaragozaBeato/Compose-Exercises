package com.example.areader.repository

import com.example.areader.data.Resource
import com.example.areader.model.Item
import com.example.areader.network.BooksApi
import javax.inject.Inject

class BookRepositoryv2 @Inject constructor(
    private val api: BooksApi
){
    suspend fun getBooks(searchQuery:String): Resource<List<Item>> {

        return try{
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items

            if(itemList.isNotEmpty()){
                Resource.Loading(false)
            }else{
                Resource.Loading(true)
            }

            Resource.Success(data = itemList)

        }catch(ex:Exception){
            Resource.Error(message = ex.message.toString())
        }
    }

    suspend fun getBookInfo(bookId:String):Resource<Item>{

        val response = try{

            Resource.Loading(data= true)
            api.getBookInfo(bookId)



        }catch (ex: Exception){
            return Resource.Error(ex.message.toString())
        }
         Resource.Loading(false)
        return Resource.Success(data = response)
    }
}