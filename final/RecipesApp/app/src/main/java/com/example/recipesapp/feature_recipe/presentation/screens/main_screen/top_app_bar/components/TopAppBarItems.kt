package com.example.recipesapp.feature_recipe.presentation.screens.main_screen.top_app_bar.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipesapp.feature_recipe.presentation.util.Colors

@Composable
fun MainTopBar(title:String,
               onNavigationClick:()-> Unit,){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp)
    ){
     Row(
         verticalAlignment = Alignment.CenterVertically
     ){
         IconButton(onClick = { onNavigationClick.invoke() }) {
             Icon(imageVector = Icons.Default.Menu,
                 contentDescription = "Menu icon",
                 tint = Colors.whitePastel)
         }
         Text(title,
             style = TextStyle(
                 fontWeight = FontWeight.Bold,
                 color = Colors.whitePastel,
                 fontSize = MaterialTheme.typography.h6.fontSize,),
             modifier = Modifier
                 .padding(start = 5.dp))
     }
    }
}

@Composable
fun ChildTopBarMeal(navController: NavHostController,
                    key:String){

    val navBackStackEntry by navController.currentBackStackEntryAsState()


    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ){
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Go Back",
                tint = Colors.whitePastel)
        }
        Text(navBackStackEntry?.arguments?.getString(key).toString(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Colors.whitePastel,
                fontSize = MaterialTheme.typography.h6.fontSize
            )
        )
    }
}