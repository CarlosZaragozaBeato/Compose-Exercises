package com.carloszaragoza.groceryapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.carloszaragoza.groceryapp.feature_main.data.data_source.ShopDatabase
import com.carloszaragoza.groceryapp.feature_main.data.repository.DataStoreRepository
import com.carloszaragoza.groceryapp.feature_login.data.repository.UserUserRepositoryImp
import com.carloszaragoza.groceryapp.feature_login.domain.use_cases.*
import com.carloszaragoza.groceryapp.feature_login.domain.repository.UserRepository
import com.carloszaragoza.groceryapp.feature_shop.data.repository.OrderRepositoryImp
import com.carloszaragoza.groceryapp.feature_shop.domain.repository.OrderRepository
import com.carloszaragoza.groceryapp.feature_shop.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideShopDatabase(app: Application):ShopDatabase {
        return Room.databaseBuilder(
            app,
            ShopDatabase::class.java,
            ShopDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            addUser = AddUserUseCase(repository),
            deleteUser = DeleteUserUseCase(repository),
            getUser = GetUserUseCase(repository),
            updateUser = UpdateUserUseCase(repository),
            getUserLoggedIn = GetUserLoggedIn(repository)
        )
    }
    @Singleton
    @Provides
    fun provideOrdersUseCases(repository: OrderRepository): OrderUseCase {
        return OrderUseCase(
            addOrder = AddOrder(repository),
            deleteAllOrders = DeleteAllOrders(repository),
            deleteOrder = DeleteOrder(repository),
            getOrders = GetOrders(repository),
            getOrdersById = GetOrderById(repository),
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(db:ShopDatabase): UserRepository {
        return UserUserRepositoryImp(db.loginDao)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(db:ShopDatabase):OrderRepository{
        return OrderRepositoryImp(db.orderDao)
    }



    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context:Context
                                    ) = DataStoreRepository(context = context)
}