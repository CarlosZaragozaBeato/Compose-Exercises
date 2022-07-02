package com.example.movieapp.navigation

import java.lang.IllegalArgumentException

enum class MovieScreens{
    HOMESCREEN,
    DETAILSSCREEN;

    companion object{
        fun fromRoute(route: String?):MovieScreens
        = when(route?.substringBefore("/")){
            HOMESCREEN.name -> HOMESCREEN
            DETAILSSCREEN.name -> DETAILSSCREEN
            null -> HOMESCREEN
            else -> throw  IllegalArgumentException("Route $route is not valid")
        }
    }
}