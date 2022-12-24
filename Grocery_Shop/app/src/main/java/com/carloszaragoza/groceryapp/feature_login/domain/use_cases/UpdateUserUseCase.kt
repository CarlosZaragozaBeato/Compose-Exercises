package com.carloszaragoza.groceryapp.feature_login.domain.use_cases

import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_login.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User){
        repository.updateUser(user)
    }
}