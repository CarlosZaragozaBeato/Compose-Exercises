package com.example.splitter.feature_calculator.presentation.components.total.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitter.constants.Colors
import com.example.splitter.feature_calculator.presentation.view_model.ViewModel


@Composable
fun ResultCard(
    text:String = "Tip Amount",
    result:MutableState<String> ,

){



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal =10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = text,
                    style = MaterialTheme.typography.h6,
                    color = Colors.White,
                    fontWeight = FontWeight.Bold)
                Text(text = "/Person",
                    color = Color.LightGray,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold)
            }
            Text(text = result.value,
                color = if(result.value == "0.0"){
                            Color.LightGray
                        }else{
                            Colors.veryLightGreen
                             },
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                )
            
        }



}