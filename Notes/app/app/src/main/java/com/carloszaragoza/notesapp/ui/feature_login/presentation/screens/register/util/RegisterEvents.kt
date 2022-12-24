package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.register.util

sealed class RegisterEvents{
    data class OnUsernameChanged(val username:String) : RegisterEvents()
    data class OnPasswordChanged(val password:String) : RegisterEvents()
    data class OnPasswordConfirmedChanged(val confirmPassword:String) : RegisterEvents()
    object OnSubmit:RegisterEvents()
    data class OnNavigate(val route:String):RegisterEvents()
}
