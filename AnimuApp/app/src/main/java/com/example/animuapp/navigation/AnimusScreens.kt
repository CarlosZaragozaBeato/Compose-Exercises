package com.example.animuapp.navigation

import java.lang.IllegalArgumentException

enum class AnimusScreens{

    HOMESCREEN,
    DETAILSSCREEN;

    companion object {
        fun fromRoute(route:String?):AnimusScreens
        = when (route?.substringBefore("/")){
            HOMESCREEN.name -> HOMESCREEN
            DETAILSSCREEN.name -> DETAILSSCREEN
            null -> HOMESCREEN
            else -> throw(IllegalArgumentException("Value $route not valid"))
        }
    }
}