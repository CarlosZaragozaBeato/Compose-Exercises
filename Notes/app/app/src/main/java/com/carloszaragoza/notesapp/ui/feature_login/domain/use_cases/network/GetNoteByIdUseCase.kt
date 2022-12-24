package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NetworkRepository
) {
    suspend operator fun invoke(user_id:Int, note_id:Int):note_db?{
        return repository.getNoteById(user_id, note_id)
    }
}