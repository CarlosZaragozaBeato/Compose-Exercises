package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import com.carloszaragoza.groceryapp.R

@Composable
fun TopBarHome(
    goToSearch:() -> Unit,
    goToProfile:() -> Unit

) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        title = {
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ){
                
                Image(painter = painterResource(id = R.drawable.icon),
                    contentDescription = "App Icon")
                
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle()
                        ){
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onBackground,
                                    fontSize = MaterialTheme.typography.h6.fontSize,
                                )
                            ){
                                append("Grocery ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Light,
                                    color = MaterialTheme.colors.onBackground,
                                    fontSize = MaterialTheme.typography.h6.fontSize

                                )
                            ){
                                append("Market")
                            }
                        }

                    }
                )



            }
        },
        actions = {
            IconButton(onClick = {goToSearch.invoke()}){
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Go to the search screen")
            }
            IconButton(onClick = {goToProfile.invoke()}){
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Go to the profile screen")
            }
        }
    )

}