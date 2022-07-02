package com.example.areader.screens.stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.sharp.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.areader.model.MBook
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.home.HomeViewModel
import com.example.areader.screens.home.components.ReaderAppBar
import com.example.areader.screens.search.BookRow
import com.example.areader.utils.formatDate
import com.google.firebase.auth.FirebaseAuth
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun StatsScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
 var books: List<MBook>
 val currentUser = FirebaseAuth.getInstance().currentUser

    
    Scaffold(topBar = {
        ReaderAppBar(title = "Book Stats",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            showProfile = false){
            navController.navigate(ReaderScreens.ReaderHomeScreen.name)
        }}) {
            Surface(){
                books = if(!viewModel.data.value.data.isNullOrEmpty()){
                    viewModel.data.value.data!!.filter {mBook->
                        mBook.userId == currentUser?.uid
                    }
                }else{
                    emptyList()
                }
                Column {
                    Row() {
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .padding(2.dp)
                        ){
                            Icon(imageVector = Icons.Sharp.Person ,
                                contentDescription = "Person Icon" )
                        }
                        Text(text = "Hi ,${currentUser?.email.toString().split("@")[0].uppercase(
                            Locale.getDefault())}")
                    }
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        shape = CircleShape,
                        elevation = 5.dp
                            ){
                        val readBookLIst: List<MBook>  = if(!viewModel.data.value.data.isNullOrEmpty()){
                            books.filter{ mBook->
                                (mBook.userId == currentUser?.uid) && (mBook.finishedReading!=null)
                            }
                        }else{
                            emptyList()
                        }

                        val readingBookLIst: List<MBook>  = books.filter{ mBook->
                                (mBook.userId == currentUser?.uid) && (mBook.finishedReading ==null) && (mBook.startedReading!=null)
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 25.dp, bottom = 4.dp, top = 4.dp ),
                            horizontalAlignment = Alignment.Start
                        ){
                            Text(text = "Your Stats",
                                style = MaterialTheme.typography.h5)

                            Divider()

                            Text(text = "You're Reading: ${readingBookLIst.size}")

                            Text(text = "You've Finished: ${readBookLIst.size}")
                    }
                    }

                    if(viewModel.data.value.loading == true){
                        LinearProgressIndicator()
                    }else{
                        Divider()
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ){
                            val readBooks:List<MBook> = if(!viewModel.data.value.data.isNullOrEmpty()){
                                viewModel.data.value.data!!.filter{ mBook->
                                    (mBook.userId == currentUser?.uid) && (mBook.finishedReading !=null)

                                }
                            }else{
                                emptyList()
                            }
                            items(readBooks){mBook->
                                BookRow2(book = mBook)
                            }
                        }
                    }

                }
            }
    }
    
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookRow2(book: MBook,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(3.dp),
        shape = RectangleShape,
        elevation = 7.dp
    ){
        Row(
            modifier = Modifier
                .padding(5.dp),
            verticalAlignment = Alignment.Top,
        ){
            val imageUrl:String =
                if(book.photoUr?.isEmpty() == true){
                    "https://img1.ak.crunchyroll.com/i/spire2/8a6279fc503fe64b451a0e63afc658281610403545_full.jpg"
                }
                else {
                    book.photoUr.toString()
                }
            Image(painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Anime IMage",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp))

            Column(){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = book.title.toString(),
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,)
                    if(book.rating!!>=4){
                        Spacer(modifier = Modifier.fillMaxWidth(.8f))
                        Icon(imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Thumb Up Icon",
                            tint = Color.Green.copy(0.4f))
                    }else{
                        Box{}
                    }
                }


                Text(text = "Authors: ${book.authors}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = true,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)


                Text(text = "Started: ${formatDate(book.startedReading!!)}",
                    overflow = TextOverflow.Clip,
                    softWrap = true,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)

                Text(text = "Finished: ${formatDate(book.finishedReading!!)}",
                    overflow = TextOverflow.Clip,
                    softWrap = true,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)


            }
 
        }

    }

}
