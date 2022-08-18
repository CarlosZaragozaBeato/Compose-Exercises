package com.example.recipesapp.feature_recipe.data.repository

import com.example.recipesapp.feature_recipe.data.data_source.RecipeDao
import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dao:RecipeDao
        ): RecipeRepository {


    override fun getRecipes(): Flow<List<Recipe>> {
            return dao.getRecipes().flowOn(Dispatchers.IO)
    }

    override suspend fun getRecipeById(id: String): Recipe? {
            return dao.getRecipeById(id)
    }

    override suspend fun insertRecipe(recipe: Recipe) {
            return dao.insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
            return dao.deleteRecipe(recipe)
    }

    override fun getFilters(): Flow<List<FilterOptions>> {
            return dao.getFilters().flowOn(Dispatchers.IO)
    }

    override suspend fun insertFilter(filter: FilterOptions) {
            return dao.insertFilter(filter)
    }

    override suspend fun updateFilter(filter: FilterOptions) {
            return dao.updateFilter(filter)
    }
}