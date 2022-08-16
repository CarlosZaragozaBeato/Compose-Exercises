package com.carloszaragoza.promodoroapp.feature_pomodoro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.navigation.PomodoroNavigation
import com.carloszaragoza.promodoroapp.feature_pomodoro.presentation.theme.PromodoroAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromodoroAppTheme {
                val navController = rememberNavController()
                PomodoroNavigation(navController = navController)
            }
        }
    }
}
