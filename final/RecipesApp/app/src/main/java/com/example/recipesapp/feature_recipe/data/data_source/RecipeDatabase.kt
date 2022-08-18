package com.example.recipesapp.feature_recipe.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe

@Database(
    entities = [Recipe::class,
                FilterOptions::class],
    version = 4,
)
abstract class RecipeDatabase:RoomDatabase() {
    abstract val recipeDao: RecipeDao

    companion object{
        const val DATABASE_NAME ="recipes_db"
    }

}