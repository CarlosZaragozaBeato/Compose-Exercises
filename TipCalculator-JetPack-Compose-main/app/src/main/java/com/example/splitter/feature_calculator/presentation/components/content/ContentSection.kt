package com.example.splitter.feature_calculator.presentation.components.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.splitter.feature_calculator.presentation.components.content.components.BillSection
import com.example.splitter.feature_calculator.presentation.components.content.components.NumberOfPeople
import com.example.splitter.feature_calculator.presentation.components.content.components.TipPercentage
import com.example.splitter.feature_calculator.presentation.components.total.TotalSection
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel

@Preview
@Composable
fun ContentSection(viewModel: ViewModel = ViewModel()){


        BillSection(viewModel = viewModel)
        TipPercentage(viewModel = viewModel)
        NumberOfPeople(viewModel = viewModel)
        TotalSection(viewModel = viewModel)
}
