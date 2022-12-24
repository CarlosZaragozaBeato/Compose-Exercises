package com.carloszaragoza.notesapp.ui.feature_login.domain.repository

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user.user
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status

interface NetworkRepository {

    suspend fun getUser(name:String,
                        password:String):user?

    suspend fun getStatus():Status?

    suspend fun postUser(user: User)

    suspend fun getNotes(name:String, password: String): note_db?

    suspend fun deleteNote(noteId: Int, userId:Int)

    suspend fun getNoteById(user_id:Int, note_id:Int):note_db?

    suspend fun createNote(noteDbitem: note_dbItem)

    suspend fun updateNote(noteDbitem: note_dbItem)
}