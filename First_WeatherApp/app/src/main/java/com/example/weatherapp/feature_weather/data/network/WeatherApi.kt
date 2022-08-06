package com.example.weatherapp.feature_weather.data.network

import com.example.weatherapp.feature_weather.data.utils.Constants
import com.example.weatherapp.feature_weather.domain.model.actual_day.ActualDay
import com.example.weatherapp.feature_weather.domain.model.seven_days.WeekTime
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
                               ):WeekTime

    @GET(value = "v1/forecast.json")
    suspend fun getDayWeather(
        @Query("key") key:String = Constants.API_KEY,
        @Query("q") query:String,
        @Query("days") days:Int = 1,
        @Query("aqi") aqi:String = "no",
        @Query("alerts") alerts:String = "no"
    ):ActualDay


}