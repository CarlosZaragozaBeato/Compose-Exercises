package com.carloszaragoza.pomodoro.feature_login.presentation.screen.add_pomodoro.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carloszaragoza.pomodoro.feature_login.presentation.theme.listOfColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputColors(
    color: MutableState<Color>,

){
    Column {
        LazyVerticalGrid(
            cells = GridCells.Fixed(5),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(listOfColor) { item ->
                Card(
                    modifier = Modifier
                        .width(10.dp)
                        .height(60.dp)
                        .padding(8.dp)
                        .clickable {
                                color.value = item
                        },
                    backgroundColor = item,
                    shape = RoundedCornerShape(20.dp)
                ){

                }
            }
        }
    }
}