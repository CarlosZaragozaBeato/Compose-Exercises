package com.example.splitter.feature_calculator.presentation.components.total

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.components.total.components.ResultCard
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel

@Preview
@Composable
fun TotalSection(
    viewModel: ViewModel = ViewModel()
){
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
        color = Colors.darkGreen
            ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
        ) {
            ResultCard(text="Tip Amount",
                result = viewModel.tipAmountPerPerson)
            Spacer(modifier = Modifier
                        .height(5.dp))
            ResultCard(text = "Total",
                result = viewModel.totalPerPerson,
                )
        }
    }

}