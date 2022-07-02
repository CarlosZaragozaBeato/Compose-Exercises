package com.example.movieapp.widgets

import android.view.animation.Transformation
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(Movie: Movie  = getMovies()[0],
             onClickItem: (String) -> Unit = {}){

    var expandedInfo by remember {
        mutableStateOf(false)
    }
    var rotationIcon by remember{
        mutableStateOf(0f)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClickItem(Movie.id)
            },
        shape = RoundedCornerShape(
            corner = CornerSize(6.dp)),
        elevation = 4.dp){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            ){
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(size = 10.dp)),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
            Image(painter = rememberAsyncImagePainter(Movie.images[0]),
                contentScale = ContentScale.Crop,
                contentDescription = "Movie Poster")

            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
            ){
                Text(text = Movie.title,
                    style = MaterialTheme.typography.h6)

                Text(text ="Director: ${Movie.director}",
                    style = MaterialTheme.typography.caption)

                Text(text = "Release: ${Movie.year}",
                    style = MaterialTheme.typography.caption)


                AnimatedVisibility(visible = expandedInfo) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )){
                                    append("Plot: ")

                                }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp
                                )){
                                append(Movie.plot)
                            }


                        },
                        modifier = Modifier
                            .padding(6.dp)
                        )
                        Divider(
                            modifier = Modifier
                                .padding(6.dp)
                        )
                        Text( text ="Director: ${Movie.director}",
                            style= MaterialTheme.typography.caption)

                        Text( text ="Actors: ${Movie.actors}",
                            style= MaterialTheme.typography.caption)

                        Text( text ="Rating: ${Movie.rating}",
                            style= MaterialTheme.typography.caption)

                    }
                }



                Icon(imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expandedInfo = !expandedInfo
                            if (expandedInfo) {
                                rotationIcon = 180f
                            } else {
                                rotationIcon = 0f
                            }
                        }
                        .rotate(rotationIcon)
                        .animateContentSize(
                            animationSpec = tween(
                                delayMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                    tint = Color.Gray)
            }

        }

    }
}