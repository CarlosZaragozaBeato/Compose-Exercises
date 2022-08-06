package com.example.weatherapp.feature_weather.presentation.screens.day.components

import android.hardware.lights.Light
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.weatherapp.feature_weather.domain.model.actual_day.ActualDay
import com.example.weatherapp.feature_weather.presentation.theme.ui.LocalSpacing


@Composable
fun Title(
    location:String,
    country:String,
    region:String,
    latitude: String,
    longitude: String

){
        Row(
            modifier = Modifier
                .padding(start = LocalSpacing.current.small,top = LocalSpacing.current.small)
                .fillMaxWidth()
                .height(100.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.7f)
            ){
              Text(location,
                  style = MaterialTheme.typography.h4,
                  color = MaterialTheme.colors.primary,
                  modifier = Modifier
                      .padding(bottom = LocalSpacing.current.extraSmall)
                      .fillMaxWidth(),
              overflow = TextOverflow.Ellipsis,
                  maxLines = 1)


             Column(){
                 Text("Country > $country",
                     style = MaterialTheme.typography.h6,
                     color = MaterialTheme.colors.primary,
                     overflow = TextOverflow.Ellipsis,
                 maxLines = 1)

                 Text(" Region > $region",
                     style = MaterialTheme.typography.caption,
                     fontWeight = FontWeight.Bold,
                     color = MaterialTheme.colors.primaryVariant,
                     overflow = TextOverflow.Ellipsis,
                     maxLines = 1)

            }
          }

            Column(
                horizontalAlignment = Alignment.End,

                modifier = Modifier
                    .padding(end = LocalSpacing.current.extraSmall)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
            ){
                Text("Lat > $latitude",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant)

                Text(" Long > $longitude",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant)
            }
        }


    }
