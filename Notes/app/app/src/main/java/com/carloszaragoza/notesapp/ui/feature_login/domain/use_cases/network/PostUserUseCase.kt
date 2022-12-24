package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import javax.inject.Inject

class PostUserUseCase @Inject constructor(
    private val repository: NetworkRepository
){
    suspend operator fun invoke(user: User){
        return repository.postUser(user)
    }
}