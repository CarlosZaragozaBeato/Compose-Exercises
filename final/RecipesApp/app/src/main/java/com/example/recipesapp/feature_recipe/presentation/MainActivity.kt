package com.example.recipesapp.feature_recipe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.recipesapp.feature_recipe.presentation.screens.main_screen.MainScreen
import com.example.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesAppTheme {
            MainScreen()
            }
        }
    }
}
