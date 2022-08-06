package com.example.weatherapp.feature_weather.data.repository

import com.example.weatherapp.feature_weather.data.data_resource.DataOrException
import com.example.weatherapp.feature_weather.data.network.CountryApi
import com.example.weatherapp.feature_weather.domain.model.country.Country
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val api: CountryApi
){

    private val dataOrException = DataOrException<Country,Boolean,Exception>()

    suspend fun getCountry(): DataOrException<Country, Boolean, Exception> {

        try {
            dataOrException.loading = true
            dataOrException.data = api.getCountries()


            if (dataOrException.data != null) {
                dataOrException.loading = false
            }
        } catch (ex: Exception) {
            dataOrException.e = ex
        }
        return dataOrException

    }
}

