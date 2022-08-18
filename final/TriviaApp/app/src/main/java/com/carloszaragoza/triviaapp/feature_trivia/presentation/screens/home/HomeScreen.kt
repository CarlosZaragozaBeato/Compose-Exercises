package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.util.UiEvent
import com.carloszaragoza.triviaapp.R
import com.carloszaragoza.triviaapp.feature_trivia.domain.model.category_model.Category
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.components.CategoryCard
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.components.HomeAppBar
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.SearchState

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate)->Unit ,
    viewModel: HomeViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }

        }
    }

    Scaffold(
        topBar = {
            HomeAppBar(
                viewModel = viewModel
            )
        }
    )
    {
        if(!viewModel.listFiltered.value.isEmpty()){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                itemsIndexed(viewModel.listFiltered.value) { index, category ->
                    CategoryCard(
                        category = category,
                        index = index
                    ) {
                        viewModel.onEvent(HomeEvents.OnQuizPage(it))
                    }
                }
            }
        }else{
            Text("Category Not Found",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = MaterialTheme.colors.onPrimary
                ) ,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp))
        }

    }
}


