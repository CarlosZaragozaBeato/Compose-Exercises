package com.carloszaragoza.triviaapp.di

import android.app.Application
import androidx.room.Room
import com.carloszaragoza.triviaapp.feature_trivia.data.network.TriviaApi
import com.carloszaragoza.triviaapp.feature_trivia.data.repository.QuestionsRepository
import com.carloszaragoza.triviaapp.feature_trivia.data.utils.Constants
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.data_model.Questions
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
    fun provideTriviaApi():TriviaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionsRepository(api:TriviaApi) = QuestionsRepository(api)
}