package com.example.weatherapp.feature_weather.presentation.screens.day.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing

@Composable
fun BodyComponent(
    image:String,
    condition:String,
    currentTmp:String,
    Wind:String,
    feelsLike:String,
    Humidity:String,
    currentGust:String,
    lastUpdated:String

){
    Row(
        modifier = Modifier
            .padding(top = LocalSpacing.current.large, start = LocalSpacing.current.small,
                    end = LocalSpacing.current.small,  )
            .fillMaxWidth()
            .height(180.dp),
        horizontalArrangement = Arrangement.SpaceBetween

    ){
        ImageCard(image = image,
                    condition = condition,
                    currentTmp = currentTmp)

        DetailsInfo(currentGust = currentGust,
                    feelsLike = feelsLike,
                    Humidity = Humidity,
                    lastUpdated = lastUpdated,
                    Wind = Wind)

    }
}

@Composable
fun DetailsInfo(
    Wind:String,
    feelsLike:String,
    Humidity:String,
    currentGust:String,
    lastUpdated:String

) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            elevation = 0.dp
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.End,

            ) {
                Text("Details".uppercase(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Light ,
                color = MaterialTheme.colors.primary,
                maxLines = 1)

                Column(
                    horizontalAlignment = Alignment.End
                ){
                    Text(text = "Wind > ${Wind}kph",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant)

                    Text(text = "Feels Like > ${feelsLike}ºC",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant)

                    Text(text = "Humidity > $Humidity",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant)

                    Text(text = "Gust(kph) > $currentGust",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant)

                    Text(text = "Last Updated > ${lastUpdated.split(" ")[1]}",
                        style = MaterialTheme.typography.caption,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant)


                }


            }
        }
}
@Composable
fun ImageCard(
    image:String,
    condition:String,
    currentTmp:String
){
    Card(
        modifier = Modifier

            .fillMaxWidth(.5f)
            .fillMaxHeight(),
        elevation = LocalSpacing.current.small
    ) {
        Column(
            modifier = Modifier
                .padding(LocalSpacing.current.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://${image}"),
                contentDescription = "image",
                modifier = Modifier
                    .size(100.dp)
            )
            Column {
                Text("Condition > $condition",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1)

                Text(text = "Temperature > ${currentTmp}ºC",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant)

            }
        }
    }
}
