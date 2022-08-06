package com.example.weatherapp.feature_weather.data.network

import com.example.weatherapp.feature_weather.domain.model.country.Country
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface CountryApi {

    @GET(value = "api/v0.1/countries/capital")
    suspend fun getCountries():Country



}