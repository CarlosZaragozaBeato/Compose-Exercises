package com.example.movieapp.screens.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController,
                  movieId: String?)    {
    val newMovie = getMovies().filter { movie->
          movie.id==movieId
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.LightGray
            ) {
                Row (

                        ){

                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription ="Arrow Back",
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                })
                        Spacer(modifier = Modifier
                            .width(25.dp))
                        Text(text = "Details")
                }

            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color.LightGray
        ){

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                MovieRow(newMovie.first())

                Spacer(modifier = Modifier
                    .height(8.dp))
                Divider()
                Text(text = "Movie Images")
                LazyRow{
                    items(newMovie.first().images){ image->
                        Card(modifier = Modifier
                            .padding(12.dp)
                            .size(240.dp),
                        elevation = 5.dp) {
                            Image(painter = rememberAsyncImagePainter(model = image)
                                , contentDescription = "Image",
                                contentScale = ContentScale.Crop)
                        }
                    }
                }
            }

        }


    }
    
    


}