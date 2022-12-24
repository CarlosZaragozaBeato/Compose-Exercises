package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NetworkRepository
){
    suspend operator fun invoke(name:String, password:String): note_db? {
        return repository.getNotes(name, password)
    }
}