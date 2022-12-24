package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import javax.inject.Inject


class GetStatusUseCase @Inject constructor(
    private val repository: NetworkRepository
){
    suspend operator fun invoke(): Status? {
        return repository.getStatus()
    }
}