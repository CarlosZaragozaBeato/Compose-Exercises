package com.carloszaragoza.layoutexample.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carloszaragoza.layoutexample.domain.model.BottomItem
import com.carloszaragoza.layoutexample.domain.model.MealPrp
import com.carloszaragoza.layoutexample.domain.model.Recipe
import com.carloszaragoza.layoutexample.presentation.navigation.Screens


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = { AppBar() },
        bottomBar = { BottomBarCustom("HOME") }
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)){
            HomeTitle()
            MealInfo()
            RecentMeals(){route ->
                navController.navigate(route)
            }
        }
    }
}


@Composable
fun BottomBarCustom(current_page: String) {
    BottomAppBar(backgroundColor = Color(0xff18AC91),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(20.dp))) {
        BottomItem.list_bottom_items.forEach { item ->
            BottomNavigationItem(selected = item.route == current_page,
                onClick = {},
                icon = { Icon(imageVector = item.icon
                    , contentDescription = item.name)},
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(.4f))

        }
    }
}

@Composable
fun RecentMeals(onNavigate:(String)->Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Recent meal",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        modifier = Modifier.padding(top = 30.dp, bottom = 10.dp))

        LazyColumn(
            modifier = Modifier.height(200.dp)
        ){
            items(Recipe.list_of_reciper){ item ->
                MealsCard(item){ foodId ->
                    onNavigate.invoke("${Screens.Details.route}/foodId=${foodId}")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MealsCard(item:Recipe, onItemNavigation:(String) -> Unit) {

    Card(modifier = Modifier.fillMaxWidth()
        .padding(vertical = 10.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(25.dp),
        onClick = {onItemNavigation.invoke(item.id)}) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
           Row(  verticalAlignment = Alignment.CenterVertically,){
               Image(painter = painterResource(id = item.image), contentDescription = item.name,
                   contentScale = ContentScale.Fit,
                   modifier = Modifier
                       .clip(CircleShape)
                       .size(60.dp))
               Text(text = item.short_name, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp ))
           }
            Text("${(item.properties.last()).quantity} Cal",
            fontWeight = FontWeight.Bold,
            color = Color(0xffF3B063),
            fontSize = 20.sp)

        }
    }

}


@Composable
fun MealInfo() {

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Recipe.list_of_reciper.get(0).properties.forEach {item ->
            if(!item.type.equals("Calories")){
                MealInfoIntem(item)
            }
        }
    }
}

@Composable
fun MealInfoIntem(item: MealPrp) {
    Column(modifier = Modifier.padding(top = 20.dp)){
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .width(90.dp)
                .height(110.dp)
                .background(Color(item.color).copy(.2f))){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("${item.percentage}%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(item.color))
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight((item.percentage / 100).toFloat())
                .background(Color(item.color).copy(.5f)))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(){
            Text(item.type, fontWeight = FontWeight.Bold)
            Text("${item.quantity}g", fontWeight = FontWeight.Normal, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun HomeTitle() {
    Spacer(modifier = Modifier.height(30.dp))

    Column(){
        Text("Did you like \nyour breakfast?",
            style = TextStyle(fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing =2.sp)
        )

        Text("You have eaten 598 calories", color = Color.Gray,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 10.dp))
    }

}


@Composable
fun AppBar() {
    TopAppBar(elevation = 0.dp, backgroundColor = Color.Transparent) {
        Row(modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,){
            Text("Icon")
            Row{
                Text(text = "Hello, Carlos")
                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Default.Person, contentDescription = "PROFILE IMAGE")
            }
        }
    }
}