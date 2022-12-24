package com.carloszaragoza.notesapp.ui.feature_login.data.network

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_db
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user.user
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.test_retrofit.model.user.notas.status.Status
import retrofit2.http.*
import javax.inject.Singleton


@Singleton
interface NotesApi {

    @GET(value = "get_user")
    suspend fun getUser(
        @Query("name")name:String,
        @Query("password")password:String):user?

    @GET(value = "message")
    suspend fun getStatus(): Status?

    @POST(value = "create_user")
    suspend fun postUser(@Body user: User)

    @GET(value = "get_notes")
    suspend fun getNotes(
        @Query("name")name:String,
        @Query("password")password:String): note_db?

    @DELETE(value = "delete")
    suspend fun deleteNote(
        @Query("noteId")noteId:Int,
        @Query("userId")userId:Int)

    @GET(value = "get_note_by_id")
    suspend fun getNoteById(
        @Query("user_id")user_id:Int,
        @Query("note_id")note_id:Int
    ):note_db?

    @POST(value = "create_note")
    suspend fun createNote(@Body note: note_dbItem)

    @PUT(value = "update_note")
    suspend fun updateNote(@Body note: note_dbItem)

}


