package com.carloszaragoza.notesapp.ui.feature_login.data.data_source

import androidx.room.*
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User


@Dao
interface UserRoomDao{

    @Query("SELECT * FROM tbl_users where :name == name AND :password == password")
    fun getUserRoom(name:String, password:String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserRoom(user:User)

    @Delete
    suspend fun deleteUserRoom(user:User)

    @Query("SELECT * FROM tbl_users where logIn == 1")
    fun getUserLoggedRoom(): User?
}