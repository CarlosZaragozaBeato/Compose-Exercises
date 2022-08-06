package com.example.weatherapp.feature_weather.data.data_resource

import java.lang.Exception

data class DataOrException<T,Boolean, E:Exception>(
    var data:T? = null,
    var loading:Boolean? = null,
    var e:E? = null
)
