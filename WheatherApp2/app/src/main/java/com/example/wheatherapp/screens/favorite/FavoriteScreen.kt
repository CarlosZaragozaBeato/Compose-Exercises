package com.example.wheatherapp.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wheatherapp.widgets.WeatherAppBar
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.example.wheatherapp.model.Favorite
import com.example.wheatherapp.navigation.Screens


@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel(),
){

    Scaffold(
        topBar = { WeatherAppBar(
            title = "Favorite cities",
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController){ navController.popBackStack()} }){
        val listFav = viewModel.favList.collectAsState().value

        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                LazyColumn(){
                    items(listFav){ item->
                        CityRow(item,navController,viewModel)

                    }
                }


            }
        }

    }

}

@Composable
fun CityRow(
            item: Favorite,
            navController: NavController,
            viewModel: FavoriteViewModel) {


    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                       navController.navigate(Screens.MAIN_SCREEN.name+"/${item.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color(0xffb2dfdb)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){

            Text(text = item.city,
                modifier = Modifier.padding(start = 4.dp))  
            
            Surface(
                modifier = Modifier 
                    .padding(0.dp),
                color = Color.LightGray,
                shape = CircleShape
            ){
                Text(text = item.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption)
            }

            Icon(imageVector =Icons.Rounded.Delete,
                contentDescription = "Delete icon",
                modifier = Modifier
                    .clickable {
                               viewModel.deleteFavorite(item)
                    },
                tint = Color.Red.copy(alpha=0.3f))
        }

    }

}
