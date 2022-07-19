package com.example.splitter.feature_calculator.presentation.components.content.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TipPercentage(
    viewModel: ViewModel = ViewModel()
) {


    Surface() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                Arrangement.SpaceBetween
            ){
                Text(text = "Select Tip %",
                    color = Colors.darkGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp))


                Text(text = viewModel.discountSelected.value.toString(),
                    color = Colors.darkGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp))

            }
            Column(){

                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(viewModel.data.value.size) { item ->

                        ButtonTip(text = viewModel.data.value.get(item)){
                            viewModel.discountSelected.value = viewModel.data.value.get(item)
                        }
                        items(1){
                            TextField(text = viewModel.discountSelected,
                                placeholder = "Max 100 pls",
                                modifier = Modifier
                                    .height(50.dp)
                                    .padding(2.dp))
                        }
                    }

                }
            }
        }
    }
}