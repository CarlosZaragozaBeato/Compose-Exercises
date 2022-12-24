package com.carloszaragoza.groceryapp.feature_login.domain.use_cases


data class UserUseCases(
    val addUser: AddUserUseCase,
    val deleteUser: DeleteUserUseCase,
    val getUser: GetUserUseCase,
    val updateUser: UpdateUserUseCase,
    val getUserLoggedIn: GetUserLoggedIn
)
