package com.carloszaragoza.deliverapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.carloszaragoza.deliverapp.feature_restaurant.data.data_source.RecipeDatabase
import com.carloszaragoza.deliverapp.feature_restaurant.data.repository.DataStoreRepository
import com.carloszaragoza.deliverapp.feature_restaurant.data.repository.RepositoryImp
import com.carloszaragoza.deliverapp.feature_restaurant.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideRecipeDatabase(app: Application):RecipeDatabase{
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            RecipeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: RecipeDatabase):Repository{
        return  RepositoryImp(db.recipeDao)
    }
}