package com.carloszaragoza.groceryapp.feature_login.data.repository

import com.carloszaragoza.groceryapp.feature_login.data.data_source.LoginDao
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_login.domain.repository.UserRepository
import javax.inject.Inject


class UserUserRepositoryImp @Inject constructor(
    private val dao: LoginDao
): UserRepository {

    override fun getUser(username: String, password: String): User {
        return dao.getUser(username, password)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(id: Int?) {
        dao.deleteUser(id)
    }

    override suspend fun updateUser(user: User) {
        dao.updateUser(user)
    }

    override fun getUserLogged(): User? {
        return dao.getUserLogged()
    }

}