package com.carloszaragoza.triviaapp.feature_trivia.data.data_source

data class DataOrException<T, Boolean, E:Exception>(
    var data:T?=null,
    var loading:Boolean? = null,
    var e:E? = null
)
