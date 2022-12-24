package com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network

data class UserUseCases(
    val getUser: GetUserUseCase,
    val getStatus: GetStatusUseCase,
    val getNotes: GetNotesUseCase,
    val postUser: PostUserUseCase,
    val deleteUser: DeleteNoteUseCase,
    val getNoteById: GetNoteByIdUseCase,
    val createNote: CreateNoteUseCase,
    val updateNote: UpdateNoteUseCase
)
