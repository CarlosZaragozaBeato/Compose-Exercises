package com.example.areader.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.areader.model.MBook
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.home.components.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    Scaffold(
        topBar = {
                 ReaderAppBar(
                     title = "A.Reader",
                     navController = navController
                 )
        },
        floatingActionButton = {
            FabContent{
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ){
        Surface(
            modifier = Modifier
                .fillMaxSize()){

            HomeContent(navController = navController,
                        viewModel)
        }
    }

}





@Composable
private fun HomeContent(navController: NavHostController,
                        viewModel: HomeViewModel){

    var listOfBooks = emptyList<MBook>()
    val currentUser = FirebaseAuth.getInstance().currentUser

    if(!viewModel.data.value.data?.isEmpty()!!){
        listOfBooks = viewModel.data.value?.data!!.toList().filter { mBook->
            mBook.userId == currentUser?.uid.toString()
        }
    }

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if(email.isNullOrEmpty()){
        "N/A"
    }else{
        email.split("@")[0]

    }

    Column(
        modifier = Modifier
            .padding(2.dp),
        verticalArrangement = Arrangement.Top) {

        Row(modifier = Modifier
            .align(alignment = Alignment.Start)){

            TitleSection(label = "Your Reading \n"+"activity right now.")
        
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            
            Column {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.StatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant)
                Text(text = currentUserName,
                    modifier = Modifier
                        .padding(2.dp),
                    style= MaterialTheme.typography.overline,
                    color = Color.Red.copy(0.6f),
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip)

                Divider()
        }

        
        }
        ReadingRightNowArea(listOfBooks = listOfBooks,
                            navController = navController)

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks, navController = navController)

    }
    

}



