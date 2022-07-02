package com.example.wheatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wheatherapp.data.DataOrException
import com.example.wheatherapp.model.Weather
import com.example.wheatherapp.model.WeatherItem
import com.example.wheatherapp.navigation.Screens
import com.example.wheatherapp.screens.main.MainViewModel
import com.example.wheatherapp.screens.settings.SettingsViewModel
import com.example.wheatherapp.utils.formatDate
import com.example.wheatherapp.utils.formatDecimals
import com.example.wheatherapp.widgets.*

@Composable
fun WeatherMainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    city: String?,
){

    val curCity: String = if (city!!.isBlank()) "Spain" else city


    val unitFromDb = settingsViewModel.listUnits.collectAsState().value
    var unit = remember{
        mutableStateOf("imperial")
    }
    var isImperial = remember{
        mutableStateOf(false)
    }

    if(unitFromDb.isNullOrEmpty()){

       unit.value =  unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial.value = unit.value == "imperial"


        val weatherData = produceState<DataOrException<Weather,
                Boolean,
                Exception >>(initialValue =
        DataOrException(loading = true) , ){

            value = viewModel.getWeatherData(city = curCity,
                                            units = unit.value)

        }.value

        if(weatherData.loading == true){
            CircularProgressIndicator()
        }else if(weatherData.data !=null){
            MainScaffold(weather = weatherData.data!!,
                navController = navController)
        }
    }



}

@Composable
fun MainScaffold(weather: Weather,
                navController: NavController) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = weather.city.name+" , "+ weather.city.country,
                navController = navController,
                elevation = 5.dp,
                onAddAction ={
                        navController.navigate(Screens.SEARCH_SCREEN.name)
                } )


        }

    ){
        MainContent(data = weather)
    }
    

}

@Composable
fun MainContent(data: Weather) {

    val imageUrl = "https://openweathermap.org/img/wn/${data.list[0].weather[0].icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){

        Text(
            text = formatDate(data.list[0].dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(6.dp)
        )
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xffFFC400)
                ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                WeatherStateImage(imageUrl = imageUrl)

                Text(formatDecimals(data.list[0].temp.day) + "°" ,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = data.list[0].weather[0].main,
                    fontStyle = FontStyle.Italic)
            }

        }

        HumidityWindPreasure(weather = data.list[0])
        Divider()

        SunsetSunriseRow(weather = data.list[0])

        Text(text = "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp))

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xffeef1ef),
            shape = RoundedCornerShape(size = 14.dp)) {
            LazyColumn(modifier = Modifier
                .padding(vertical = 2.dp),
            contentPadding = PaddingValues(1.dp)) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weather = item)

                }
            }
    }
}
}

