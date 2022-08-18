package com.example.recipesapp.feature_recipe.domain.use_case

import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow


class GetFilter (
    private val repository: RecipeRepository
) {
    operator fun invoke(): Flow<List<FilterOptions>> {
        return repository.getFilters()
    }
}