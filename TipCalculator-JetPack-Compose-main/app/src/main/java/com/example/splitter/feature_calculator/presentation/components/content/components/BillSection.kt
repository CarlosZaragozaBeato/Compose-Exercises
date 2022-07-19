package com.example.splitter.feature_calculator.presentation.components.content.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel
import kotlinx.coroutines.flow.asStateFlow

@Preview
@Composable
fun BillSection(
    viewModel: ViewModel = ViewModel()
){
    Surface() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text("Bill",
                color =  Colors.darkGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 10.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                TextField(text = viewModel.bill,
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = "120",
                        opcion = false,
                )
            }



        }

    }
}