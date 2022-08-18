package com.example.recipesapp.feature_recipe.domain.model.recipe

data class LocalRecipe(
    val id:String,
    val categories: List<String>,
    val title: String,
    val affordability: Affordability,
    val complexity: Complexity,
    val image:String,
    val duration:Int,
    val ingredients:List<String>,
    val steps:List<String>,
    val isGlutenFree:Boolean,
    val isVegan:Boolean,
    val isVegetarian:Boolean,
    val isLactoseFree:Boolean
)

enum class Affordability{
    Affordable,
    Pricey,
    Luxurious
}

enum class Complexity{
    Challenging,
    Simple,
    Hard
}