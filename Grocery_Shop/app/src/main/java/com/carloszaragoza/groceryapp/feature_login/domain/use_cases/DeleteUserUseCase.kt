package com.carloszaragoza.groceryapp.feature_login.domain.use_cases

import com.carloszaragoza.groceryapp.feature_login.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
){
    suspend operator fun invoke (id:Int?){
        repository.deleteUser(id)
    }
}