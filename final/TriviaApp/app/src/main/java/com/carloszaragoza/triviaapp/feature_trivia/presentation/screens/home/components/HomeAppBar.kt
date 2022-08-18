package com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carloszaragoza.triviaapp.R
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.HomeViewModel
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.HomeEvents
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.home.utils.SearchState
import com.carloszaragoza.triviaapp.feature_trivia.presentation.screens.quiz.QuizViewModel

@Composable
fun HomeAppBar(
        viewModel: HomeViewModel
){
    when(viewModel.searchState.value){
         SearchState.OPENED -> {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f),
                title = {
                        TextField(value = viewModel.searchValue.value,
                                onValueChange = {
                                      viewModel.onEvent(HomeEvents.OnSearchFilterCategory(it))
                                },
                                placeholder = {Text("Search here...")},
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                ),)
                },
                actions = {
                   Image(painter = painterResource(id = R.drawable.close),
                        contentDescription = "Close Icon",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(30.dp)
                            .clickable {
                                viewModel.updateSearchState(SearchState.CLOSED)
                            })
                }
            )


         }
        SearchState.CLOSED ->{
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.2f),
                title = {
                    Column {
                        Text(text = "Let's Play",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.h4.fontSize,
                                    color =MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold

                            )
                        )

                        Text(text = "See the first",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.caption.fontSize,
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                actions = {
                    Image(painter = painterResource(id = R.drawable.search),
                        contentDescription ="Watermelon Image",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 15.dp)
                            .clickable {
                                viewModel.updateSearchState(SearchState.OPENED)
                            })
                }
            )
        }
    }
}