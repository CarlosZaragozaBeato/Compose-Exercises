package com.carloszaragoza.notesapp.di

import android.app.Application
import androidx.room.Room
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.NetworkRepository
import com.carloszaragoza.notesapp.ui.feature_note.data.data_source.NoteDatabase
import com.carloszaragoza.notesapp.ui.feature_note.data.repository.RepositoryImp
import com.carloszaragoza.notesapp.ui.feature_note.domain.repository.Repository
import com.carloszaragoza.notesapp.ui.feature_login.data.repository.UsersRepositoryImp
import com.carloszaragoza.notesapp.ui.feature_login.data.network.NotesApi
import com.carloszaragoza.notesapp.ui.feature_login.data.repository.UserRoomRepositoryImp
import com.carloszaragoza.notesapp.ui.feature_login.domain.repository.UserRoomRepository
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.network.*
import com.carloszaragoza.notesapp.ui.feature_login.domain.use_cases.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): Repository {
        return RepositoryImp(db.orderDao)
    }

    @Provides
    @Singleton
    fun provideUserRoomRepository(db: NoteDatabase): UserRoomRepository {
        return UserRoomRepositoryImp(db.loginDao)
    }

    @Provides
    @Singleton
    fun provideUsersRoomUseCase(repository: UserRoomRepository): UsersRoomCases{
        return UsersRoomCases(
            getUser = GetUserRoomUseCase(repository),
            addUser = AddUserRoomUseCase(repository),
            deleteUser = DeleteUserRoomUseCase(repository),
            getUserLoggedIn = GetUserRoomLoggedIn(repository)
        )
    }


        @Singleton
        @Provides
        fun provideNotesApi(): NotesApi {
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.141:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NotesApi::class.java)
        }


        @Singleton
        @Provides
        fun provideUsersApiRepository(api: NotesApi):NetworkRepository
                                    = UsersRepositoryImp(api)

    @Singleton
    @Provides
    fun provideUsersUseCases(repository: UsersRepositoryImp): UserUseCases {
        return UserUseCases(
            getUser = GetUserUseCase(repository),
            getStatus  = GetStatusUseCase(repository),
            postUser = PostUserUseCase(repository),
            getNotes = GetNotesUseCase(repository),
            deleteUser = DeleteNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository),
            createNote = CreateNoteUseCase(repository),
            updateNote = UpdateNoteUseCase(repository))
        }
}