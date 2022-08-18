package com.example.recipesapp.feature_recipe.domain.repository

import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(): Flow<List<Recipe>>

    suspend fun getRecipeById(id:String):Recipe?

    suspend fun insertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe:Recipe)

    fun getFilters(): Flow<List<FilterOptions>>

    suspend fun insertFilter(filter: FilterOptions)


    suspend fun updateFilter(filter:FilterOptions)
}