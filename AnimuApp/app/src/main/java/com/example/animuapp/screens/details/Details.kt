package com.example.animuapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animuapp.model.getAnimu
import com.example.animuapp.widgets.AnimuCard

@Composable
fun DetailsPage(
    navController: NavController,
    animuId: String?
){

    val animu = getAnimu().filter{ Anime ->
         Anime.name == animuId
    }

    Scaffold(
        topBar ={
            TopAppBar(
                backgroundColor = Color(0xff112031),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row {
                        Icon(imageVector = Icons.Default.ArrowBack ,
                            contentDescription = "Arrow Back",
                            tint = Color(0xffF2EBE9),
                            modifier = Modifier
                                .clickable {
                                    navController.popBackStack()
                                })
                        Spacer(modifier = Modifier
                            .width(25.dp))
                        Text(text = "Animu",
                        fontSize = 20.sp,
                        color = Color(0xffF2EBE9),
                        fontWeight = FontWeight.Bold)
                    }

                }

            }
        }

    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()

        ) {
            Surface(
                modifier = Modifier,
                color = Color(0xffF2EBE9)

            ) {
                Column() {
                    AnimuCard(Animu = animu.first())

                    LazyRow(
                        modifier = Modifier
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        items(animu.first().listImages){ image ->
                            Card(
                                modifier = Modifier
                                    .size(300.dp)
                                    .padding(6.dp)
                            ) {
                                Image(painter = rememberAsyncImagePainter(model = image),
                                    contentDescription ="Image",
                                    contentScale = ContentScale.Crop)
                            }
                        }
                    }
                }

            }

        }


    }
}

