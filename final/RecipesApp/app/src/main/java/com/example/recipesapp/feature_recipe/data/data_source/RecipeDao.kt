package com.example.recipesapp.feature_recipe.data.data_source

import androidx.room.*
import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao{

    @Query("SELECT * FROM recipe_table")
    fun getRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE id = :id")
    suspend fun getRecipeById(id:String):Recipe?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe:Recipe)

    @Delete
    suspend fun deleteRecipe(recipe:Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilter(filter:FilterOptions)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFilter(filter:FilterOptions)

    @Query("SELECT * FROM filter_table")
    fun getFilters(): Flow<List<FilterOptions>>


}