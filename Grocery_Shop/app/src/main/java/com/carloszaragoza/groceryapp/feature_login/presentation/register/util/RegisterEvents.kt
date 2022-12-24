package com.carloszaragoza.groceryapp.feature_login.presentation.register.util

sealed class RegisterEvents{
    data class OnUsernameChange(val username:String): RegisterEvents()
    data class OnPasswordChange(val password:String): RegisterEvents()
    data class OnConfirmPasswordChange(val password:String): RegisterEvents()
    object OnRegister: RegisterEvents()
    data class OnNavigate(val route:String): RegisterEvents()
}
