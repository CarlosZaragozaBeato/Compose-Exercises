package com.example.wheatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.wheatherapp.R
import com.example.wheatherapp.model.WeatherItem
import com.example.wheatherapp.utils.formatDate
import com.example.wheatherapp.utils.formatDateTime
import com.example.wheatherapp.utils.formatDecimals

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(horizontal = 3.dp, vertical = 10.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
        ){
            Text(text = formatDate(weather.dt)
                .split(",")[0],
                modifier = Modifier
                    .padding(start = 5.dp))

            WeatherStateImage(imageUrl = imageUrl)

            Surface(
                color = Color(0xffFFC400),
                modifier = Modifier
                    .padding(0.dp),
                shape = CircleShape
            ) {
                Text(text = weather.weather[0].description,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier .padding(4.dp) )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = buildAnnotatedString{
                    withStyle(style = SpanStyle(
                        color = Color.Blue.copy(0.7f),
                        fontWeight = FontWeight.SemiBold
                    )){
                        append(formatDecimals(weather.temp.max) +"°")
                    }
                    withStyle(style = SpanStyle(
                        color = Color.LightGray,
                        fontWeight = FontWeight.SemiBold
                    )){
                        append(formatDecimals(weather.temp.min) +"°")
                    }
                }

                )
            }

        }



    }

}

@Composable
fun SunsetSunriseRow(weather: WeatherItem) {

    Row(modifier = Modifier
        .padding(vertical = 12.dp, horizontal = 6.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription ="Sunrise Icon",
                modifier = Modifier.size(25.dp))
            Text(text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.caption)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription ="Sunrise Icon",
                modifier = Modifier.size(25.dp))
            Text(text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.caption)
        }

    }

}

@Composable
fun HumidityWindPreasure(weather: WeatherItem) {

    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Row(modifier = Modifier
            .padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.humidity}%",
                style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier
            .padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Preassure icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.pressure}psi",
                style = MaterialTheme.typography.caption)
        }


        Row(modifier = Modifier
            .padding(4.dp)){
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.clouds}mph",
                style = MaterialTheme.typography.caption)
        }



    }




}

@Composable
fun WeatherStateImage(imageUrl: String) {

    Image(painter = rememberAsyncImagePainter( imageUrl ),
        contentDescription = "icon Image",
        modifier = Modifier.size(80.dp))

}
