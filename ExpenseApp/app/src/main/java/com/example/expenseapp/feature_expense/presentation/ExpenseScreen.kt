package com.example.expenseapp.feature_expense.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.R
import com.example.expenseapp.feature_expense.core.Colors
import com.example.expenseapp.feature_expense.presentation.components.BottomSheetContent
import com.example.expenseapp.feature_expense.presentation.components.Chart
import com.example.expenseapp.feature_expense.presentation.components.ExpenseList
import com.example.expenseapp.feature_expense.presentation.view_model.viewModelExpense
import kotlinx.coroutines.launch


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ExpenseScreen(
    viewModelExpense: viewModelExpense = hiltViewModel()
) {

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    var list = viewModelExpense.listOfExpenses.collectAsState()

    BottomSheetScaffold(
        floatingActionButtonPosition = FabPosition.Center,

        scaffoldState = scaffoldState,
        sheetPeekHeight = 15.dp,
        sheetElevation = 0.dp,
        sheetGesturesEnabled = true,

        sheetContent = {
            BottomSheetContent(viewModelExpense = viewModelExpense)

        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expense App",
                        style = TextStyle(
                            color = Colors.white,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    IconButton(onClick = {     scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    } }) {
                        Icon(
                            imageVector = Icons.Default.Add, contentDescription = "Add Icon",
                            tint = Colors.white
                        )
                    }
                },
                backgroundColor = Colors.brown,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                },
                backgroundColor = Colors.brown
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Add icon",
                    tint = Colors.white
                )
            }
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            if(!list.value.isNullOrEmpty()) {
                Chart(viewModel = viewModelExpense)
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight(.1f)
                )
                ExpenseList(viewModelExpense = viewModelExpense)
            }else{
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Text("No items in the list.", style = TextStyle(
                                color = Colors.brown,
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                        ))
                        Spacer(modifier = Modifier
                            .height(20.dp))
                        Image(painter = painterResource(id = R.drawable.zzz), contentDescription = "Empty List Image",
                            modifier = Modifier
                                .size(60.dp)
                        )


                }
            }



        }


    }
}



