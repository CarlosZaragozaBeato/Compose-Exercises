package com.carloszaragoza.groceryapp.feature_main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.ShopNavigation
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.GroceryAppTheme
import com.carloszaragoza.groceryapp.feature_main.presentation.util.LaunchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var launchViewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroceryAppTheme {
                val screen by launchViewModel.startDestination
                val navController = rememberNavController()

                ShopNavigation(navController = navController,
                                startDestination = screen)
            }
        }
    }
}