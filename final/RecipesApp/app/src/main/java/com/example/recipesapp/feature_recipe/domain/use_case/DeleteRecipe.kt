package com.example.recipesapp.feature_recipe.domain.use_case

import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository

class DeleteRecipe (
    private val repository: RecipeRepository
        ) {
    suspend operator fun invoke(recipe: Recipe){
        repository.deleteRecipe(recipe)
    }
}