package com.carloszaragoza.pomodoro.feature_login.presentation.screen.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.pomodoro.feature_login.domain.model.Pomodoro

@SuppressLint("InvalidColorHexValue")
@Composable
fun NormalPomodoro(
    item: Pomodoro,
    onSelectPomodoro:(Pomodoro) -> Unit,

){
    Box(
        modifier = Modifier
            .padding( 5.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(CornerSize(10.dp)))
            .background(Color(item.color))
            .clickable {
                onSelectPomodoro.invoke(item)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 10.dp)

        ){
            Card(modifier = Modifier
                .size(100.dp),
                backgroundColor = Color(0xC000000),
                elevation = 0.dp,

                shape = RoundedCornerShape(CornerSize(100.dp)),

                ){
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.background(Color.Transparent)
                ){
                    Text(
                        text = (item.focusTime.toInt()).toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp)
                    )
                }
            }
            Text(
                text = item.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,

                ),

                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(
                    top = 20.dp,
                    bottom = 20.dp)

            )

        }
    }
}