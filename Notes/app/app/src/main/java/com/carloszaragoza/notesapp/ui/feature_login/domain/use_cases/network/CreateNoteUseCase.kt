package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: NetworkRepository
) {
    suspend operator fun invoke(note: note_dbItem){
        return repository.createNote(note)

    }
}