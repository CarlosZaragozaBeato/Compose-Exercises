package com.example.wheatherapp.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wheatherapp.model.Favorite
import com.example.wheatherapp.navigation.Screens
import com.example.wheatherapp.screens.favorite.FavoriteViewModel
import kotlinx.coroutines.flow.filter


@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController? = null,
    viewModel: FavoriteViewModel = hiltViewModel(),
    onAddAction: () -> Unit = {},
    onButtonClick: () -> Unit = {},
){

    val showDialog = remember {
        mutableStateOf(false)
    }


    val showIt = remember{
        mutableStateOf(false)
    }

    val context = LocalContext.current


    if(showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog,
                                navController)
    }




    TopAppBar(
        title = {
                Text(text = title,
                    style = TextStyle(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    ))
        },
        actions = {
                  if(isMainScreen){
                      IconButton(onClick = {
                          onAddAction.invoke()
                      }) {
                          Icon(imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon")
                      }
                      IconButton(onClick = {
                          showDialog.value = !showDialog.value
                      }) {
                          Icon(imageVector = Icons.Rounded.MoreVert,
                              contentDescription = "More Options Icon")
                      }
                  }else Box{}
        },
        navigationIcon = {
            if(icon != null) {
                Icon(imageVector = icon, contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClick.invoke()
                    })
            }

            if(isMainScreen){

                val isAllreadyFavList = viewModel.favList
                    .collectAsState().value.filter { item->
                        (item.city == title.split(",")[0])
                    }

                if(isAllreadyFavList.isEmpty()){
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription ="Favorite Icon",
                        modifier = Modifier
                            .scale(.9f)
                            .clickable {

                                val favoriteItem = title.split(",")

                                viewModel.insertFavorite(Favorite(
                                    city = favoriteItem[0],
                                    country = favoriteItem[1]
                                )).run {
                                    showIt.value = true
                                }
                            }
                            .padding(start = 10.dp),
                        tint = Color.Red.copy(alpha =.7f))
                }else{
                    showIt.value = false
                    Box{}
                }
                showToast(context = context, showIt = showIt)


            }


        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )


}

fun showToast(context: Context, showIt: MutableState<Boolean>) {

    if(showIt.value == true){
        Toast.makeText(context, "Added To Favorites",Toast.LENGTH_SHORT).show()
    }


}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>,
                            navController: NavController?) {

    var expanded by remember {
        mutableStateOf(true)
    }

    val items = listOf(
        "About",
        "Favorites",
        "Settings"
    )


    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 12.dp)) {

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        showDialog.value = false
                    },
                ) {
                    Icon(imageVector = when (text) {
                        "About" -> Icons.Default.Info
                        "Favorites" -> Icons.Default.FavoriteBorder
                        else -> Icons.Default.Settings

                    },
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(text = text,
                        modifier = Modifier
                            .clickable {
                                navController?.navigate(
                                    when (text) {
                                        "About" -> Screens.ABOUT_SCREEN.name
                                        "Favorites" -> Screens.FAVORITE_SCREEN.name
                                        else -> Screens.SETTINGS_SCREEN.name
                                    }
                                )
                            })
                }


            }
        }

    }

}
