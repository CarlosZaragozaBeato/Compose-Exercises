package com.example.jettrivia2.feature_trivia.domain.data

 class DataOrException <T,Boolean,E:Exception> (
    var data: T? = null,
    var loading: Boolean? = null,
    var e:E ? = null
        ) {

 }