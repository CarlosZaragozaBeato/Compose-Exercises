package com.example.jettrivia2.feature_trivia.di

import com.example.jettrivia2.feature_trivia.core.Constats
import com.example.jettrivia2.feature_trivia.data.network.QuestionApi
import com.example.jettrivia2.feature_trivia.domain.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionApi():QuestionApi{

        return Retrofit.Builder()
            .baseUrl(Constats.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(api:QuestionApi) = QuestionRepository(api)
}