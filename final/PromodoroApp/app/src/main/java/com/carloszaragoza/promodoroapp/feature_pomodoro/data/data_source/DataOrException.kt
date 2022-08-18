package com.carloszaragoza.promodoroapp.feature_pomodoro.data.data_source

import java.lang.Exception

data class DataOrException<T,Boolean, E: Exception>(
    var data:T? = null,
    var loading:Boolean? = null,
    var e:E? = null
)
