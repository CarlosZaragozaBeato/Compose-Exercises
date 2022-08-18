package com.example.recipesapp.feature_recipe.domain.use_case

import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository

class GetRecipe (
    private val repository: RecipeRepository
        ){

    suspend operator fun invoke(id:String): Recipe?{
        return repository.getRecipeById(id)
    }
}