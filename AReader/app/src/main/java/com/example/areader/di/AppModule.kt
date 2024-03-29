package com.example.areader.di

import com.example.areader.network.BooksApi
import com.example.areader.repository.BookRepository
import com.example.areader.repository.FireRepository
import com.example.areader.utils.Constants.BASE_URL
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireBookRespository()
        = FireRepository(queryBook = FirebaseFirestore.getInstance()
            .collection("books"))


    @Singleton
    @Provides
    fun provideBookRepository(api:BooksApi) = BookRepository(api)


    @Singleton
    @Provides
    fun provideBookApi():BooksApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }



}