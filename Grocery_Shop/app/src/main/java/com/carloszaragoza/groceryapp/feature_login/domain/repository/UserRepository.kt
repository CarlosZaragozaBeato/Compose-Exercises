package com.carloszaragoza.groceryapp.feature_login.domain.repository

import com.carloszaragoza.groceryapp.feature_main.domain.model.User

interface UserRepository {

    fun getUser(username:String,password:String): User?

    fun getUserLogged():User?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(id: Int?)

    suspend fun updateUser(user: User)


}