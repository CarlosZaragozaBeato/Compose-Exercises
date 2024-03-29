package com.carloszaragoza.layoutexample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.layoutexample.presentation.navigation.ScreenNavigation
import com.carloszaragoza.layoutexample.presentation.theme.LayoutExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutExampleTheme {
                val navController = rememberNavController()

                ScreenNavigation(navController = navController)
            }
        }
    }
}

