package com.example.areader.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.home.components.ReaderAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.areader.data.Resource
import com.example.areader.model.Item
import com.example.areader.model.MBook
import com.example.areader.screens.home.components.RoundedButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun BookDetailsScreen(
    navController: NavHostController,
    bookId: String,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {

    val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()){
        value = viewModel.getBookInfo(bookId)
    }.value


    Scaffold(topBar = {
        ReaderAppBar(title = "Book Detail",
                    navController = navController,
                    showProfile = false,
                    icon= Icons.Default.ArrowBack){
            navController.navigate(ReaderScreens.SearchScreen.name)
        }
    }
    ) {


        Surface(
            modifier = Modifier
                .padding(3.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if(bookInfo.data == null){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()) {
                        LinearProgressIndicator()

                    }
                }else{


                    ShowDetails(bookInfo, navController)

               
                }
            }
        }
    }
}

@Composable
fun ShowDetails(bookInfo: Resource<Item>, navController: NavHostController) {


    val bookData = bookInfo.data?.volumeInfo
    val googleBookId= bookInfo.data?.id

    Card(
        modifier = Modifier
            .padding(34.dp),
        shape = CircleShape,
        elevation = 4.dp
    ){

        Image(painter = rememberAsyncImagePainter(model =bookData?.imageLinks?.thumbnail ),
            contentDescription = "Image of ${bookData?.title}",
            modifier = Modifier
                .size(90.dp)
                .padding(1.dp),
            contentScale = ContentScale.Crop)
        
    }

        Text(text = bookData?.title.toString(),
                style = MaterialTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = 19)

        Text(text = "Authors: ${bookData?.authors.toString()}")
        Text(text = "Page Count: ${bookData?.pageCount.toString()}")
        Text(text = "Categories: ${bookData?.categories.toString()}",
            style = MaterialTheme.typography.subtitle1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
            )
        Text(text = "Published: ${bookData?.publishedDate.toString()}",
            style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier
            .height(5.dp))


    val cleanDescription = HtmlCompat.fromHtml(bookData?.description.toString(),
                                                HtmlCompat.FROM_HTML_MODE_LEGACY).toString()


    val localDims = LocalContext.current.resources.displayMetrics
    Surface(
        modifier = Modifier
            .height(localDims.heightPixels.dp.times(0.09f))
            .padding(4.dp),
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Gray)
    ){
        LazyColumn(modifier = Modifier
            .padding(3.dp)){
            item {
                Text(text = cleanDescription )
            }
        }
    }


    Row(
        modifier = Modifier
            .padding(top = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        RoundedButton(label = "Save") {
            val book = MBook(
                title = bookData?.title.toString() ,
                authors = bookData?.authors.toString() ,
                description = bookData?.description.toString(),
                categories = bookData?.categories.toString(),
                notes = "",
                photoUr = bookData?.imageLinks?.thumbnail.toString(),
                publishedDate = bookData?.publishedDate.toString(),
                pageCount = bookData?.pageCount.toString(),
                rating = 0.0,
                googleBookId = googleBookId,
                userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
            )
            saveToFirebase(book, navController)
            
        }

        Spacer(modifier = Modifier.width(25.dp))

        RoundedButton(label = "Cancel") {
            navController.popBackStack()
        }
    }
}

fun saveToFirebase(book: MBook,navController: NavHostController) {
    val db = FirebaseFirestore.getInstance()
    val dbCollection = db.collection("books")

    if(book.toString().isNotEmpty()){
        dbCollection.add(book)
            .addOnSuccessListener { documentRef->
                val docId = documentRef.id
                dbCollection.document(docId)
                    .update(hashMapOf("id" to docId) as Map<String, Any>)
                    .addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            navController.popBackStack()
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Failure", "saveToFirebase: ${it.message.toString()}")
                    }
            }
    }else{

    }



}
