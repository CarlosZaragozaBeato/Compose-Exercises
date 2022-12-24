package com.carloszaragoza.groceryapp.feature_shop.presentation.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carloszaragoza.groceryapp.feature_main.presentation.navigation.Routes
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_main.presentation.util.UiEvent
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category
import com.carloszaragoza.groceryapp.feature_shop.presentation.categories.util.CategoriesEvent

@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel = viewModel(),
    onPop:()->Unit,
    onNavigate:(UiEvent.OnNavigate)->Unit
) {

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{event->
            when(event){
                is UiEvent.OnNavigate -> onNavigate(event)
                is UiEvent.OnPop -> onPop
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { onPop.invoke() }) {
                        Icon(imageVector = Icons.Default.ArrowBack ,
                            contentDescription = "Go to home page")
                    }
                },
                title = {
                    Text("Categories",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground
                    )
                }
            )
        }
    ) {

        LazyColumn(){
            items(Category.listOfCategories){ item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable{
                            viewModel.onEvent(CategoriesEvent.OnNavigate(
                                route = "${Routes.CATEGORY.name}/${item.id}"
                            ))
                        }
                        .padding(
                            vertical = LocalSpacing.current.medium,
                            horizontal = LocalSpacing.current.small
                        ),
                    backgroundColor = Color(item.color)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(LocalSpacing.current.medium),
                        horizontalArrangement = Arrangement.SpaceBetween ,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                       Text(item.name,
                            style = MaterialTheme.typography.h4,
                            color = Color(0xff000000).copy(.8f),
                            fontWeight = FontWeight.Bold,
                           modifier = Modifier
                           .fillMaxWidth(.4f),
                       maxLines = 2,
                       overflow = TextOverflow.Ellipsis)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.6f),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Image(painter = painterResource(id = item.image),
                                contentDescription = item.name,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
            }
        }

    }

}