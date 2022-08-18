package com.example.recipesapp.feature_recipe.presentation.screens.main_screen.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipesapp.feature_recipe.domain.model.drawer.MenuItem
import com.example.recipesapp.feature_recipe.presentation.util.Colors


@Composable
fun DrawerHeader(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Colors.lightBrownPastel),

    )   {
        Text("Cooking Up!!", fontSize = 30.sp,
            color = Colors.redPastel,
            fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(vertical = 40.dp, horizontal = 20.dp))
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(items[index])
                    }
                    .padding(start = 10.dp,  top = 25.dp)

            ) {
                Icon(
                    imageVector = items[index].icon,
                    contentDescription = items[index].contentDescription
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = items[index].title,
                    style = itemTextStyle,

                )
            }
        }
    }
}
