package com.carloszaragoza.groceryapp.feature_shop.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import  androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body.CardItem
import com.carloszaragoza.groceryapp.feature_shop.presentation.home.util.HomeEvents
import com.carloszaragoza.groceryapp.feature_shop.presentation.search.components.CardCategory
import com.carloszaragoza.groceryapp.feature_shop.presentation.search.util.SearchEvents

@Composable
fun SearchScreen(
    viewModel:SearchViewModel = hiltViewModel(),
    onNavigate: (UiEvent.OnNavigate) -> Unit,
    onPop:() -> Unit
) {

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnNavigate -> onNavigate(event)
                is UiEvent.OnPop -> onPop()
                else -> Unit
            }
        }
    }

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon ={
                    IconButton(onClick = { viewModel.onEvent(SearchEvents.OnPop) }) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = MaterialTheme.colors.onBackground)
                }
                                },
                title = {
                    TextField(value = viewModel.filterText.value,
                        onValueChange ={viewModel.onEvent(SearchEvents.OnChangeFilerText(it))},
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            unfocusedLabelColor = Color.Transparent,//MaterialTheme.colors.onBackground,
                            cursorColor = MaterialTheme.colors.onBackground,
                            unfocusedIndicatorColor = Color.Transparent
                    ),
                        trailingIcon = {
                                      Icon(imageVector = Icons.Default.Search,
                                          contentDescription = "Search Icon")
                        },
                        maxLines = 1)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = LocalSpacing.current.medium),
            verticalArrangement = Arrangement.Top,

        ) {

            if (state.value.listCategories.value.isNotEmpty()) {

               Column(){
                   Text("Categories",
                    style = MaterialTheme.typography.h4,
                   color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .padding(LocalSpacing.current.small))
                   LazyRow(
                       modifier = Modifier
                           .fillMaxWidth()
                   ){
                       items(state.value.listCategories.value) { item->
                           CardCategory(item = item){category->
                               viewModel.onEvent(SearchEvents.OnNavigate(
                                   route = "${Routes.CATEGORY.name}/${category}"))
                           }
                       }
                   }
               }
            }
            Spacer(modifier = Modifier.height(LocalSpacing.current.large))
            if (state.value.listItems.value.isNotEmpty()) {
                Column(){
                    Text("Ingredients",
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .padding(LocalSpacing.current.small))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(state.value.listItems.value) { item ->
                        CardItem(item = item) {item->
                            viewModel.onEvent(SearchEvents.OnAddItem(item))
                        }
                    }
                }
                }
            }
        }

    }
    
}