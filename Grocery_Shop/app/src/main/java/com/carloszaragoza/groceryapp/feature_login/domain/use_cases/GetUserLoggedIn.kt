package com.carloszaragoza.groceryapp.feature_login.domain.use_cases

import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_login.domain.repository.UserRepository
import javax.inject.Inject

class GetUserLoggedIn @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): User?{
        return repository.getUserLogged()
    }
}