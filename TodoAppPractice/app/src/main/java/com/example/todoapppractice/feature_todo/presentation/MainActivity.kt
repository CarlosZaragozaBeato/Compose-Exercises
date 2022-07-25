package com.example.todoapppractice.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.todoapppractice.feature_todo.presentation.navigation.TodoNavigation
import com.example.todoapppractice.feature_todo.presentation.theme.TodoAppPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppPracticeTheme {
            val navController = rememberNavController()
                TodoNavigation(navController = navController)
            }
        }
    }
}

