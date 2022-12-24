package com.carloszaragoza.groceryapp.feature_login.data.data_source

import androidx.room.*
import com.carloszaragoza.groceryapp.feature_main.domain.model.User

@Dao
interface LoginDao {

    @Query("SELECT * FROM user_tbl where :username == username AND :password == password")
    fun getUser(username:String,password:String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM user_tbl WHERE id == :id")
    fun deleteUser(id: Int?)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user:User)

    @Query("SELECT * FROM user_tbl where logIn == 1")
    fun getUserLogged():User?

}