package com.example.animuapp.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animuapp.model.Animu
import com.example.animuapp.model.getAnimu
import com.example.animuapp.navigation.AnimusScreens
import com.example.animuapp.widgets.AnimuCard

@Composable
fun HomeScreen(navController:NavController){
    MainContent( navController = navController)
}


@Composable
fun MainContent(animus:List<Animu> = getAnimu(),
                navController: NavController
                ){
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .height(70.dp),
                backgroundColor = Color(0xff112031),
                elevation = 0.dp
            ) {
                Text(text = "Animus App".uppercase(),
                    modifier = Modifier
                        .padding(start = 10.dp),
                    style = TextStyle(
                        color = Color(0xffEEEEEE),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ))
            }
        }
    ){
        Column(
            modifier = Modifier
                .background(Color(0xFFCECECE))
        ) {

            LazyColumn(

            ){
                items(items = animus){
                    AnimuCard(Animu = it){ animu ->
                        navController.navigate(route = AnimusScreens.DETAILSSCREEN.name+"/$animu")
                    }
                    Divider()
                }
            }
        }
    }



}
