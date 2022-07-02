package com.example.jettrivia2.feature_trivia.presentation.trivia_app

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jettrivia2.feature_trivia.core.AppColors
import com.example.jettrivia2.feature_trivia.presentation.QuestionDisplay
import com.example.jettrivia2.feature_trivia.presentation.components.ProgressBar
import com.example.jettrivia2.feature_trivia.presentation.components.QuestionCount


@Composable
fun QuestionScreen(
    viewModel: QuestionViewModel = hiltViewModel()
){
    
if (viewModel.data.value.data!=null){
    QuestionDisplay(viewModel = viewModel)
}


    




}