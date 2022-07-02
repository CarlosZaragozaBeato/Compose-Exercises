package com.example.animuapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.animuapp.model.Animu
import com.example.animuapp.model.getAnimu


@Preview
@Composable
fun AnimuCard(Animu: Animu = getAnimu()[0],
              onClickItem: (String) -> Unit= {} ){

    var expandedState by remember {
        mutableStateOf(false)
    }

    var rotateState by remember{
        mutableStateOf(0f)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable {
                onClickItem(Animu.name)
            },
        elevation = 1.dp,
        shape = RectangleShape,
        backgroundColor = Color(0xff112031)

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp)),

                ) {
                Image(painter = rememberAsyncImagePainter(Animu.image),
                      contentDescription = "Animu Image",
                      contentScale = ContentScale.Crop)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ){
                Column {
                    Text(text = Animu.name,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = MaterialTheme.typography.h3.fontWeight,
                            color = Color(0xffD7E9F7)

                        )
                    )
                    Spacer(modifier = Modifier
                        .height(5.dp))
                    Text(text = "Type: ${Animu.type}",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.body1.fontSize,
                            fontWeight = MaterialTheme.typography.h3.fontWeight,
                            color = Color(0xffD7E9F7)

                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AnimatedVisibility(visible = expandedState) {
                        Column {
                            Text(
                                buildAnnotatedString{
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xCCEEEEEE)
                                        )){
                                        append("Info: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xCCEEEEEE)
                                        )){
                                        append(Animu.info)
                                    }
                                },
                                modifier = Modifier
                                    .padding(9.dp))

                            Divider(
                                modifier = Modifier
                                    .padding(9.dp),
                                color = Color(0xCCEEEEEE)
                            )
                            Spacer(modifier = Modifier
                                .height(5.dp))
                            Text(
                                buildAnnotatedString{
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFEEEEEE)
                                        )){
                                        append("Episodes: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFEEEEEE)
                                        )){
                                        append(Animu.episodes)
                                    }
                                },)
                            Text(
                                buildAnnotatedString{
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFEEEEEE)
                                        )){
                                        append("Year: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFEEEEEE)
                                        )){
                                        append(Animu.year)
                                    }
                                },)

                        }

                    }

                    Icon(imageVector = Icons.Filled.ArrowDropDown ,
                        contentDescription = "Icon Arrow",
                        tint = Color(0xffEEEEEE),
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                expandedState = !expandedState
                                if (expandedState) rotateState = 180f else rotateState = 0f
                            }
                            .rotate(rotateState))
                }

            }

        }
    }
}