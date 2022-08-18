package com.example.recipesapp.di

import android.app.Application
import androidx.room.Room
import com.example.recipesapp.feature_recipe.data.data_source.RecipeDao
import com.example.recipesapp.feature_recipe.data.data_source.RecipeDatabase
import com.example.recipesapp.feature_recipe.data.repository.RecipeRepositoryImpl
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository
import com.example.recipesapp.feature_recipe.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRecipeDataBase(app: Application):RecipeDatabase{
        return Room.databaseBuilder(
            app,
            RecipeDatabase::class.java,
            RecipeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


@Provides
    @Singleton
    fun provideRecipeRepository(db:RecipeDatabase):RecipeRepository{
        return RecipeRepositoryImpl(db.recipeDao)
    }

    @Provides
    @Singleton
    fun provideRecipeUseCases(repository:RecipeRepository):RecipeUseCases{
        return RecipeUseCases(
            addRecipe = AddRecipe(repository),
            deleteRecipes = DeleteRecipe(repository),
            getRecipe = GetRecipe(repository),
            getRecipes = GetRecipes(repository),
            addFilter = AddFilter(repository),
            getFilter = GetFilter(repository),
            updateFilter = UpdateFilter(repository)
        )
    }

}