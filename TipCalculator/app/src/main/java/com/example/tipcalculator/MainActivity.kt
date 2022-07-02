package com.example.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.Widgets.RoundIconButton
import com.example.tipcalculator.components.InputFile
import com.example.tipcalculator.util.calculateTotal
import com.example.tipcalculator.util.calculateTotalTip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MainContent()
            }
        }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipCalculatorTheme {
        Surface(color = MaterialTheme.colors.background){
            content()
        }
    }
}






@Composable
fun TopHeader(totalPerPerson:Double){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(150.dp)
            //* .clip(shape =  RoundedCornerShape(corner = CornerSize(12.dp)))
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
            color = Color(0xffe9d7f7)
    ){
        Column(
            modifier = Modifier
                .padding(all = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

        ) {
                val total = "%.2f".format(totalPerPerson)
                Text(text = "Total per Person",
                        style = MaterialTheme.typography.h5)
                Text(text = "$$total",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.ExtraBold)
        }
    }
}


@Preview
@Composable
fun MainContent(){

    val splitByState = remember{
        mutableStateOf(1)
    }

    val tipAmountState = remember {
        mutableStateOf(0.0 )
    }
    val totalPerPerson = remember{
        mutableStateOf(0.0)
    }

    BillForm(
            splitByState = splitByState,
            tipAmountState = tipAmountState,
            totalPerPerson = totalPerPerson
    ){}
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    range: IntRange = 1..100,
    splitByState:MutableState<Int>,
    tipAmountState:MutableState<Double>,
    totalPerPerson: MutableState<Double>,
    onValueChange: (String) -> Unit = {}
){
    val totalBillState = remember{
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value){
        totalBillState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    val percentage = (sliderPositionState.value*100).toInt()


    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {



    TopHeader(totalPerPerson = totalPerPerson.value)
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ){
        Column(
            modifier = Modifier
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            InputFile(
                valueState = totalBillState,
                labelId = "Enter Bill",
                enable = true ,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValueChange(totalBillState.value.trim())
                    keyboardController?.hide()
                })
                if(validState){
                    Row(
                        modifier = Modifier
                            .padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(text = "Split",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically))
                        Spacer(modifier = Modifier
                            .width(120.dp))
                        Row(modifier = Modifier
                            .padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End){

                            RoundIconButton(
                                    imageVector = Icons.Default.Remove,
                                    onClick = {
                                        if(splitByState.value>range.first()){
                                            splitByState.value -=1
                                        }
                                        totalPerPerson.value = calculateTotal(totalBillState.value.toDouble(),splitByState.value,percentage)

                                    })

                            Text(text ="${splitByState.value}",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 9.dp, end = 9.dp))

                            RoundIconButton(
                                imageVector = Icons.Default.Add,
                                onClick ={
                                if (splitByState.value<range.last()) {
                                    splitByState.value+=1
                                }
                                    totalPerPerson.value = calculateTotal(totalBillState.value.toDouble(),splitByState.value,percentage)

                                })
                        }
                    }
                //*Tip Section
                      Row(
                          modifier = Modifier
                              .padding(horizontal = 3.dp,
                                        vertical = 12.dp)

                      ){
                          Text(text = "Tip",
                          modifier =Modifier
                              .align(Alignment.CenterVertically))
                          Spacer(
                              modifier = Modifier
                                  .width(200.dp)
                          )
                          Text(text = "$${tipAmountState.value}",
                                modifier = Modifier
                                    .align(Alignment.CenterVertically))
                      }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "${percentage}%")
                        Spacer(modifier = Modifier
                            .height(14.dp))

                        Slider(value = sliderPositionState.value,
                            onValueChange = { newVal ->
                                sliderPositionState.value = newVal
                                tipAmountState.value = calculateTotalTip(totalBill = totalBillState.value.toDouble(), tipPercentage = percentage)
                                totalPerPerson.value = calculateTotal(totalBillState.value.toDouble(),splitByState.value,percentage)

                                            },
                            modifier = Modifier
                                .padding(start = 16.dp,
                                    end = 16.dp),
                            steps = 5,
                            onValueChangeFinished = {

                            })
                    }
                }else{
                    Box(){}
                }
        }
    }
}
}


