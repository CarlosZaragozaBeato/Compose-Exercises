package com.example.recipesapp.feature_recipe.domain.use_case

import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipes (
    private val repository: RecipeRepository
        ) {
    operator fun invoke(): Flow<List<Recipe>> {
        return repository.getRecipes()
    }
}