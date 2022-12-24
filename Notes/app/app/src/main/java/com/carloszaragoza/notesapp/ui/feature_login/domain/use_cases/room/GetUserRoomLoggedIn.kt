package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room

import com.carloszaragoza.notesapp.ui.feature_login.domain.model.user_room.User
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.UserRoomRepository
import javax.inject.Inject

class GetUserRoomLoggedIn @Inject constructor(
    private val repositoryImp: UserRoomRepository
){

     operator fun invoke():User?{
       return repositoryImp.getUserLogged()
    }
}