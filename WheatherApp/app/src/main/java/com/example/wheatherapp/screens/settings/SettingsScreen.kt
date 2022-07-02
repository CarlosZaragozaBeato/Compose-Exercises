package com.example.wheatherapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wheatherapp.widgets.WeatherAppBar
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wheatherapp.model.Unit

@Composable
fun SettingsScreen(navController: NavController,
                    settingsViewModel: SettingsViewModel = hiltViewModel()){

    val unitToggleState = remember{
        mutableStateOf(false)
    }
    val measurementUnits = listOf(
        "Imperial (F)",
        "Metric (C)"
    )
    val choiceFromDb = settingsViewModel.listUnits.collectAsState().value
    val choiceDef = remember{
        mutableStateOf(0)
    }
    val defaultChoice = if(choiceFromDb.isNullOrEmpty()) measurementUnits[0]
                            else choiceFromDb[0].unit

    var choiceState  = remember{
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Settings",
                icon = Icons.Default.ArrowBack,
                isMainScreen = false,
                navController = navController
            ){
                navController.popBackStack()
            }
        }
    ){

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Change Unit Of Measurement",
                    modifier = Modifier
                        .padding(bottom = 15.dp))
                IconToggleButton(
                    checked = !unitToggleState.value,
                    onCheckedChange ={
                        unitToggleState.value = !it
                        if(unitToggleState.value){
                            choiceState.value = "Imperial (F)"
                        }else{
                            choiceState.value = "Metric (C)"

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f)))
                {
                    Text(text = if(unitToggleState.value){
                                    "Farenheit"
                                }else{
                                    "Celsius"
                    }
                    )
                }

                Button(onClick = {
                        settingsViewModel.deleteAll()
                        settingsViewModel.insertUnit(
                            Unit(
                                unit = choiceState.value
                            )
                        )
                },
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xffefbe42)
                    )) {
                    Text(
                        text = "Save",
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.White,
                        fontSize = 17.sp,
                    )
                    
                }
            }
        }
    }

}