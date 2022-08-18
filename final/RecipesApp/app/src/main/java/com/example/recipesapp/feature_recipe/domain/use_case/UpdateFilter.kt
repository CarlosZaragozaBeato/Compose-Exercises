package com.example.recipesapp.feature_recipe.domain.use_case

import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository


class UpdateFilter (
    private val repository: RecipeRepository
){
    suspend operator fun invoke(filter: FilterOptions){
        repository.updateFilter(filter)
    }
}