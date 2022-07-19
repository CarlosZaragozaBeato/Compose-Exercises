package com.example.splitter.feature_calculator.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel


@Composable
fun AppBar(
    viewModel:ViewModel
){

    TopAppBar(
        backgroundColor = Colors.lightGreen,
        modifier = Modifier
            .height(100.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically

        ) {

            Column() {
                Button(onClick = { viewModel.calculate() }
                ,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Colors.darkGreen
                    )) {
                    Text( text = "Calculate",
                        color = Color(0xffEEEEEE),
                        fontWeight = FontWeight.Bold)
                }
                Button(onClick = {
                    viewModel.reset()
                },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Colors.darkGreen
                    )) {
                    Text( text = "Reset",
                        color = Color(0xffEEEEEE),
                        fontWeight = FontWeight.Bold)
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(.6f)
            ) {
                Text(text = "SPLI",
                    modifier = Modifier
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp)

                Text(text = "TTER",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    letterSpacing = 1.sp)
            }
        }

    }
}