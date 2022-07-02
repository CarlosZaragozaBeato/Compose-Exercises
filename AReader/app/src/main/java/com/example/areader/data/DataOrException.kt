package com.example.areader.data

import com.example.areader.model.MBook

data class DataOrException<T, Boolean, E:Exception>(
    var data:T? = null,
    var loading: Boolean? = null,
    var e:E? = null
) 
