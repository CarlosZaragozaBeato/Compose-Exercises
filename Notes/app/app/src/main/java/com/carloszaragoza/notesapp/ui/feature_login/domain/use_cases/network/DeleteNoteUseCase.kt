package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NetworkRepository
) {
    suspend operator fun invoke(noteId:Int, userId:Int){
        repository.deleteNote(noteId, userId)
    }
}