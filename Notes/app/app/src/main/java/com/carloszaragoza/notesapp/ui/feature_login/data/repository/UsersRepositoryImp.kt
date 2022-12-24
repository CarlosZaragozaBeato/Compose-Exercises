package com.carloszaragoza.notesapp.ui.feature_login.data.repository


import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user.user
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import com.carloszaragoza.notesapp.ui.feature_login.data.network.NotesApi
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    private val api: NotesApi
): NetworkRepository {

    override
    suspend fun getUser(name:String,
                                 password:String): user? {
        return api.getUser(name =name,
                            password = password)
    }

    override suspend fun getStatus(): Status? {
        return api.getStatus()
    }

    override suspend fun postUser(user: User) {
        return api.postUser(user)
    }

    override suspend fun getNotes(name: String, password: String): note_db? {
        return api.getNotes(name, password)
    }

    override suspend fun deleteNote(noteId: Int, userId: Int) {
        api.deleteNote(noteId, userId)
    }

    override suspend fun getNoteById(user_id: Int, note_id: Int): note_db? {
        return api.getNoteById(user_id, note_id)
    }

    override suspend fun createNote(noteDbitem: note_dbItem) {
             api.createNote(noteDbitem)
    }

    override suspend fun updateNote(noteDbitem:note_dbItem) {
            return api.updateNote(noteDbitem)
    }


}