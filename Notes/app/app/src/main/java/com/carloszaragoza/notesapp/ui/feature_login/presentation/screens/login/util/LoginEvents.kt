package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.login.util

sealed class LoginEvents{
    data class OnUsername(val username:String):LoginEvents()
    data class OnPassword(val password:String):LoginEvents()
    object OnLogin:LoginEvents()
    data class OnNavigate(val route:String):LoginEvents()
}
