package com.example.portfoliocards

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.portfoliocards.ui.theme.PortfolioCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateBizCard(){
    val buttonState = remember{
        mutableStateOf(false)
    }
    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(20.dp),
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            elevation = 4.dp){
            Column(modifier = Modifier
                .height(200.dp)
                .padding(vertical = 10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally){
                ProfileImage()

                Divider(
                    Modifier.padding(top = 10.dp),
                    color = Color.Gray,
                    thickness = 2.5.dp
                )
                InfoSection()
                Button(onClick = {
                        buttonState.value=!buttonState.value
                }) {
                    Text(text = "Portfolio",
                    style= MaterialTheme.typography.button)
                }
                if (buttonState.value){
                    Content()
                }else{
                    Box(){

                    }
                }
                
            }

        }


    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    PortfolioCardsTheme{
        Content()
    }
}

/*Content Section*/
@Composable
private fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                border = BorderStroke(width=2.dp, color = Color.LightGray)
            ) {

            Portfolio(data = listOf<String>("Project 1","Project 2","Project 3"))
        }
    }
}

/*Image Section*/
@Composable
private fun ProfileImage(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .size(150.dp)
        .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)) {
        Image(painter = painterResource(id = R.drawable.user), contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop)
    }
}


/*Info Section*/
@Composable
private fun InfoSection() {
    Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Miles P.+",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
            )
        Text(text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp),
            )

        Text(text = "@themilesCompose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.subtitle1
           )
    }
}

/*Portfolio Section*/
@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){ it ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ){
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(16.dp)
                ){
                    ProfileImage(modifier = Modifier
                        .size(80.dp))
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    ){
                        Text(text = it,fontWeight=FontWeight.Bold)
                        Text(text = "A great project.", style = MaterialTheme.typography.body2)
                    }


                }
            }

        }
    }
}
/*@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    PortfolioCardsTheme{
        CreateBizCard()
    }
}
*/