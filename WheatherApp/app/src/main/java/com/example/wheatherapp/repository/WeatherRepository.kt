package com.example.wheatherapp.repository

import android.util.Log
import com.example.wheatherapp.data.DataOrException
import com.example.wheatherapp.model.Weather
import com.example.wheatherapp.model.WeatherObject
import com.example.wheatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api:WeatherApi
){


    suspend fun getWeather(city: String, unit: String):DataOrException<Weather,
                                                                        Boolean,
                                                                        Exception>{

        val responds = try{
            api.getWeather(query = city,
                            units = unit)
        }catch (e:Exception){
            Log.d("ERROR", "getWeather: $e")
            return DataOrException(e = e)

        }
        return DataOrException(data = responds)

    }


}