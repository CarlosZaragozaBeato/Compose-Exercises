package com.carloszaragoza.notesapp.ui.feature_login.domain.repository

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User

interface UserRoomRepository {

    suspend fun getUser(name:String, password:String): User?

    suspend fun insertUser(user:User)

    suspend fun deleteUser(user: User)

    fun getUserLogged(): User?
}