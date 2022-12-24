package com.carloszaragoza.groceryapp.feature_shop.presentation.user.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.carloszaragoza.groceryapp.feature_main.domain.model.User

data class UserState(
    var user: User? = null,
    var isLoading:MutableState<Boolean> =mutableStateOf(true)
)
