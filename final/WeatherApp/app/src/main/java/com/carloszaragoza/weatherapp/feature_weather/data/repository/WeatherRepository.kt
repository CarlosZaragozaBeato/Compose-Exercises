package com.carloszaragoza.weatherapp.feature_weather.data.repository

import com.carloszaragoza.weatherapp.feature_weather.data.data_resource.DataOrException
import com.carloszaragoza.weatherapp.feature_weather.data.network.WeatherApi
import com.carloszaragoza.weatherapp.feature_weather.domain.model.favorites.week_time.WeekTime
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
){

    private val dataOrExceptionWeek = DataOrException<WeekTime,Boolean,Exception>()



    suspend fun getWeekWeather(searchQuery:String): DataOrException<WeekTime, Boolean, Exception> {
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




}