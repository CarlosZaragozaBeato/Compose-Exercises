package com.example.splitter.feature_calculator.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.components.AppBar
import com.example.splitter.feature_calculator.presentation.components.content.ContentSection
import com.example.splitter.feature_calculator.presentation.components.content.components.ButtonTip
import com.example.splitter.feature_calculator.presentation.components.content.components.TextField
import com.example.splitter.feature_calculator.presentation.components.total.components.ResultCard
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorScreen(
    viewModel:ViewModel = ViewModel()
){
    Scaffold(
        topBar = {
            AppBar(viewModel = viewModel)
                    })

        {
        Surface() {
         Column(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 20.dp)
         ) {
             ContentSection(viewModel = viewModel)
         }
        }

    }
}