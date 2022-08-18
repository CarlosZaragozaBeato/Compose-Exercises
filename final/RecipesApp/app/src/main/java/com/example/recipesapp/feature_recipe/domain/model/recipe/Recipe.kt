package com.example.recipesapp.feature_recipe.domain.model.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_table")
data class Recipe(
    @PrimaryKey
    val id:String,
)



