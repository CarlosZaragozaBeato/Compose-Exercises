package com.carloszaragoza.triviaapp.feature_trivia.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.triviaapp.feature_trivia.presentation.navigation.TriviaNavigation
import com.carloszaragoza.triviaapp.feature_trivia.presentation.theme.TriviaAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TriviaAppTheme {
                val navController = rememberNavController()
                
                TriviaNavigation(navController = navController)

            }
        }

    }
}

