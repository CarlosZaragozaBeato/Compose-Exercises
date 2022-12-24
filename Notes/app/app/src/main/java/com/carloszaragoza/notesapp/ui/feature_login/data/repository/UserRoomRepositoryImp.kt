package com.carloszaragoza.notesapp.ui.feature_login.data.repository

import com.carloszaragoza.notesapp.ui.feature_login.data.data_source.UserRoomDao
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.UserRoomRepository
import javax.inject.Inject

class UserRoomRepositoryImp @Inject constructor(
    private val dao: UserRoomDao
): UserRoomRepository {

    override suspend fun getUser(name: String, password: String): User{
        return dao.getUserRoom(name, password)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUserRoom(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUserRoom(user)
    }

    override fun getUserLogged(): User? {
       return dao.getUserLoggedRoom()
    }
}