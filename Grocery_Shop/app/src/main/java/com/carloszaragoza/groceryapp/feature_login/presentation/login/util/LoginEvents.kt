package com.carloszaragoza.groceryapp.feature_login.presentation.login.util

sealed class LoginEvents{
    data class OnUsernameChange(val username:String): LoginEvents()
    data class OnPasswordChange(val password:String): LoginEvents()
    object OnLogin:LoginEvents()
    data class OnNavigate(val route:String):LoginEvents()
}
