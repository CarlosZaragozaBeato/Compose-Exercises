package com.carloszaragoza.deliverapp.feature_restaurant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.navigation.RestaurantNavigation
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.LaunchViewModel
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.DeliverAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashLaunchViewModel: LaunchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliverAppTheme {
                val screen by splashLaunchViewModel.startDestination
                val navController = rememberNavController()
                RestaurantNavigation(navController = navController, startDestination = screen)
            }
        }
    }
}