package com.example.wheatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.wheatherapp.data.WeatherDao
import com.example.wheatherapp.data.WeatherDataBase
import com.example.wheatherapp.model.WeatherObject
import com.example.wheatherapp.network.WeatherApi
import com.example.wheatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDataBase):WeatherDao
    = database.weatherDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): WeatherDataBase
    = Room.databaseBuilder(
        context,
        WeatherDataBase::class.java,
        "weather_db"
    ).fallbackToDestructiveMigration()
        .build()





    @Singleton
    @Provides
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }



}