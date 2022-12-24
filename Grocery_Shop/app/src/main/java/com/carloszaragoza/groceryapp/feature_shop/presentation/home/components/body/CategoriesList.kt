package com.carloszaragoza.groceryapp.feature_shop.presentation.home.components.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carloszaragoza.groceryapp.feature_main.presentation.theme.LocalSpacing
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Category

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesList(
    onNavigate:(String) -> Unit,
    toCategoryScreen:() -> Unit
){

    Column(){
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .padding(start = LocalSpacing.current.large,
                        top = LocalSpacing.current.large,
                        bottom = LocalSpacing.current.small)
       ) {
           Text("Categories",
            style = MaterialTheme.typography.h4,
           color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold)
           IconButton(onClick = { toCategoryScreen.invoke() }) {
               Icon(imageVector = Icons.Default.ArrowForward,
                   contentDescription = "Go to the categories page.")
           }
       }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(Category.listOfCategories){ item ->
                Card(
                    modifier = Modifier
                        .padding(horizontal = LocalSpacing.current.extraSmall)
                        .width(170.dp)
                        .height(120.dp),

                    onClick = {
                        onNavigate(item.id)
                    },
                    shape = RoundedCornerShape(LocalSpacing.current.large),
                    backgroundColor = Color(item.color),

                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = LocalSpacing.current.medium,
                                    bottom = LocalSpacing.current.small),
                        horizontalAlignment = Alignment.End
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .fillMaxHeight(.7f),
                        ){
                            Image(painter = painterResource(id = item.image),
                                contentDescription = item.name,
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .fillMaxSize())
                        }
                        Text(item.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff000000).copy(.8f),
                                fontSize = 16.sp
                            ),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }



}