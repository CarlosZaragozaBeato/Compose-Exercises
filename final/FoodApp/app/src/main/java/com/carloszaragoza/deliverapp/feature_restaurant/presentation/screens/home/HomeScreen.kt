package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category.Category
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.BodyTitle
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.CategoriesList
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.HomeScreenTopBar
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.bottom_bar.BottomItems
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.category_body.CategoryBody
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components.recipe_body.RecipeBody
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils.bottom_navigation_class.BottomItem
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.theme.LocalSpacing
import com.carloszaragoza.deliverapp.feature_restaurant.presentation.util.UiEvent
import kotlinx.coroutines.flow.collect


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
){
    val currentChart = viewModel.currentOrder.collectAsState(initial = emptyList())


    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.onNavigation -> navController.navigate(event.route)
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = viewModel.currentLocation.value){
        viewModel.onEvent(HomeEvents.FilterList)
        viewModel.onEvent(HomeEvents.FilterRecipe)

    }

    Scaffold(
        topBar = {
            HomeScreenTopBar(viewModel = viewModel,
                            currentChart = currentChart)},
        bottomBar = {
            BottomItems(navController = navController,
                        viewModel = viewModel)
        })
    {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = LocalSpacing.current.extraLarge)
        ){
            BodyTitle()
            CategoryBody(viewModel = viewModel)
            RecipeBody(viewModel = viewModel,
                        ubication = viewModel.currentLocation.value.ubication)
        }
    }
}