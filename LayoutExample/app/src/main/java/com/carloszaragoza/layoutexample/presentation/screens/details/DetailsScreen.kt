package com.carloszaragoza.layoutexample.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.layoutexample.domain.model.MealPrp
import com.carloszaragoza.layoutexample.domain.model.Recipe


@Preview
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val current_rcp: Recipe = viewModel.current_recipe.value!!

    Scaffold(backgroundColor = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(top = 25.dp)){
            DetailsTitle(current_rcp)
            RecipeMainInfo(item = current_rcp)
            DescriptionRecipe(item = current_rcp)
            ButtonRecipe()
        }
    }
}


@Composable
fun ButtonRecipe() {
    Box(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight(),
        contentAlignment = Alignment.Center){
       Button(onClick = {},
        modifier = Modifier
            .fillMaxWidth(.8f)
            .height(60.dp),
           shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xff4FB79F),
            contentColor = Color(0xffFFFFFF)
        ) ){
           Text("It was delicious",
            fontSize = 20.sp,
           fontWeight = FontWeight.SemiBold)
       }
    }
}

@Composable
fun DescriptionRecipe(item: Recipe) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)){
        Text("Description".uppercase(), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(item.description,
            fontSize = 18.sp,
            color = Color.Gray,
            letterSpacing = .25.sp
            ,lineHeight = 25.sp)
    }
}


@Composable
fun RecipeMainInfo(item:Recipe) {
    Row(
        modifier = Modifier
            .padding(top = 50.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(id = item.image),
            contentDescription = item.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth(.45f)
                .height(250.dp)
                .offset(x = (2).dp)
                .clip(RoundedCornerShape(20.dp)))

        InfoRecipeCards(items = item.properties)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoRecipeCards(items:List<MealPrp>) {
        LazyVerticalGrid(cells = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.SpaceAround){
            items(items){ item ->
                RecipePropertyInfo(item)
            }
    }
}

@Composable
fun RecipePropertyInfo(item:MealPrp) {
    Card(modifier = Modifier
        .padding(10.dp)
        .height(120.dp), shape = RoundedCornerShape(40.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Text(text = item.quantity, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = item.type)
        }
    }
}


@Composable
fun DetailsTitle(item: Recipe) {
    Column(modifier = Modifier.padding(horizontal = 20.dp)){
        Text(item.name,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp)
        Text("${item.preparation} preparation",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xffF3B063),
            modifier = Modifier.padding(top = 5.dp))


    }
}