package com.carloszaragoza.notesapp.ui.feature_login.presentation.screens.access_options

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.carloszaragoza.notesapp.R
import com.carloszaragoza.notesapp.ui.navigation.Routes
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing

@Composable
fun AccessScreen(
    onNavigate: (String) -> Unit
) {
    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.medium),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Box(modifier = Modifier
               .fillMaxHeight(.7f),
           contentAlignment = Alignment.Center){
               Image(painter = painterResource(id = R.drawable.login),
                   contentDescription = "Access Image",
               contentScale = ContentScale.FillBounds,
               modifier = Modifier
                   .fillMaxSize())
           }
            Row(
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .height(LocalSpacing.current.extraLarge)
                    .clip(RoundedCornerShape(LocalSpacing.current.medium)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { onNavigate.invoke(Routes.REGISTER.name) },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.onBackground,
                        contentColor = MaterialTheme.colors.background
                    ),
                    shape = RectangleShape,
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )) {
                    Text("Register")
                }
                Button(onClick = {  onNavigate.invoke(Routes.LOGIN.name) },
                    shape = RectangleShape,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.onBackground.copy(.5f),
                                contentColor = Color(0xffFFFFFF)
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )){
                    Text("Sign In")
                }
            }
        }
    }
}