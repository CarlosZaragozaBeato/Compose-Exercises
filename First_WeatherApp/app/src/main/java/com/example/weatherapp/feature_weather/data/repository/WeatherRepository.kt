package com.example.weatherapp.feature_weather.data.repository

import com.example.weatherapp.feature_weather.data.data_resource.DataOrException
import com.example.weatherapp.feature_weather.data.network.WeatherApi
import com.example.weatherapp.feature_weather.domain.model.actual_day.ActualDay
import com.example.weatherapp.feature_weather.domain.model.seven_days.WeekTime
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api:WeatherApi
){

    private val dataOrExceptionWeek = DataOrException<WeekTime,Boolean,Exception>()
    private val dataOrExceptionDay = DataOrException<ActualDay, Boolean, Exception>()


    suspend fun getWeekWeather(searchQuery:String): DataOrException<WeekTime,Boolean,Exception>{
        try{
            dataOrExceptionWeek.loading = true
            dataOrExceptionWeek.data = api.getWeekWeather(query = searchQuery)


            if(dataOrExceptionWeek.data != null){
                dataOrExceptionWeek.loading = false
            }
        }catch (ex:Exception){
            dataOrExceptionWeek.e = ex
        }
        return dataOrExceptionWeek
    }


    suspend fun getDayWeather(searchQuery:String): DataOrException<ActualDay,Boolean,Exception>{
        try{
            dataOrExceptionDay.loading = true
            dataOrExceptionDay.data = api.getDayWeather(query = searchQuery)


            if(dataOrExceptionDay.data != null){
                dataOrExceptionDay.loading = false
            }
        }catch (ex:Exception){
            dataOrExceptionDay.e = ex
        }
        return dataOrExceptionDay
    }



}