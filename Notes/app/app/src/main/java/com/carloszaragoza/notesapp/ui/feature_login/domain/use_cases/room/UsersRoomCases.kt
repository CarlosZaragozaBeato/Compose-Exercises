package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room

data class UsersRoomCases(
    val addUser: AddUserRoomUseCase,
    val deleteUser: DeleteUserRoomUseCase,
    val getUser: GetUserRoomUseCase,
    val getUserLoggedIn: GetUserRoomLoggedIn
)
