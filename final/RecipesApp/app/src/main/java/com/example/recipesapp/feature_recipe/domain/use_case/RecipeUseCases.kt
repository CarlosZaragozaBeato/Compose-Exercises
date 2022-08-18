package com.example.recipesapp.feature_recipe.domain.use_case

data class RecipeUseCases(
    val getRecipes:GetRecipes,
    val deleteRecipes:DeleteRecipe,
    val addRecipe:AddRecipe,
    val getRecipe:GetRecipe,
    val addFilter: AddFilter,
    val updateFilter: UpdateFilter,
    val getFilter: GetFilter
)
