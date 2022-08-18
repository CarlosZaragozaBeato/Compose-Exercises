package com.carloszaragoza.weatherapp.feature_weather.data.network

import com.carloszaragoza.weatherapp.feature_weather.data.utils.Constants
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {

    @GET(value = "v1/forecast.json")
    suspend fun getWeekWeather(
        @Query("key") key:String = Constants.API_KEY,
        @Query("q") query:String,
        @Query("days") days:Int = 7,
        @Query("aqi") aqi:String = "no",
        @Query("alerts") alerts:String = "no"
                               ): WeekTime




}