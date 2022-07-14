package com.example.expenseapp.feature_expense.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import androidx.compose.ui.unit.dp
import com.example.expenseapp.feature_expense.core.Colors

@Composable
fun BottomSheetContent(
    viewModelExpense: viewModelExpense = hiltViewModel()
){

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    Surface (
        modifier = Modifier

            .fillMaxWidth()
            .height(screenHeight / 2),
        color = Colors.lightBrow
            ){
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(10.dp)
        ) {
            TextFieldTransaction(text = viewModelExpense.title.value!!,
                label = "Title",
                modifier = Modifier.fillMaxWidth(.7f)){
                viewModelExpense.title.value = it
            }
            TextFieldTransaction(text = viewModelExpense.amount.value!!,
                label="Amount",
                modifier = Modifier.fillMaxWidth(.7f),
                keyboardType = KeyboardType.Number){
                viewModelExpense.amount.value = it
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                DatePicker()
                Spacer(modifier = Modifier.height(5.dp))
                Box(contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxWidth()){

                    ButtonTransaction(text = "Add Transaction"){

                        if(viewModelExpense.date.value == null ||
                                viewModelExpense.amount.value!!.isEmpty() ||
                                viewModelExpense.title.value!!.isEmpty()){

                            viewModelExpense.date.value = null
                            viewModelExpense.title.value = ""
                            viewModelExpense.amount.value = ""
                        }else{
                            viewModelExpense.addTransaction()
                            viewModelExpense.date.value = null
                            viewModelExpense.title.value = ""
                            viewModelExpense.amount.value = ""
                        }
                    }
                }
            }

            

        }

    }

}