package com.example.composepractice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.ComposePracticeTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposePracticeTheme {

                MainContent()
                }
            }
        }
    }


@Preview
@Composable
fun MainContent(){
    var moneyCounter = remember{mutableStateOf(0)}
    Surface(
        modifier= Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFF2D1D1)
    ){

            Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "$${moneyCounter.value}",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = MaterialTheme.typography.h4.fontSize,
                        fontWeight = MaterialTheme.typography.h2.fontWeight

                    ))
                Spacer(modifier = Modifier
                    .height(30.dp))
                CreateCircle(moneyCounter = moneyCounter.value){ newValue->
                    moneyCounter.value = newValue
                }
            }
           }

    }

//*@Preview
@Composable
fun CreateCircle(moneyCounter:Int = 0, updatedCounter: (Int)-> Unit){

    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(85.dp)
            .clickable {
                updatedCounter(moneyCounter + 1)
            },
            shape = CircleShape,
            elevation = 5.dp
    ) {
        Box(
            contentAlignment = Alignment.Center,

        ){
            Text(text = "Tap $moneyCounter")
        }

    }
}




//*Header
@Composable
fun Header(){
    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(all = 25.dp)
    ) {
        Title()
        ShowAge(age = 23)
    }
}
//*Header Title
@Composable
private fun Title() {
    Text("Carlao",
        color = MaterialTheme.colors.onPrimary,
        fontSize = 50.sp,
        fontWeight = FontWeight(800),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth())
}
//*Header Info
@Composable
fun ShowAge(age:Int){
    Text(text = "Your age is $age",
        color = MaterialTheme.colors.onPrimary,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        )
}

//*Body
@Composable
fun Body(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Red)
    ){

        Text(text = "·Haasdpashdaisdbghaisdgbsdasd",
        color = Color.White)

    }
}