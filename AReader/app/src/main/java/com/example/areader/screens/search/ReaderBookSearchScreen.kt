package com.example.areader.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.home.components.ReaderAppBar
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.areader.components.InputField
import com.example.areader.model.MBook
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.areader.model.Book
import com.example.areader.model.Item

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchBookViewModelv2 = hiltViewModel(),
) {



    Scaffold(
        topBar = {
            ReaderAppBar(
                        title = "Search Books",
                        navController =navController,
                        icon = Icons.Default.ArrowBack,
                        showProfile = false,
                        ){
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            }
        }
    ){
        Surface(){
            Column {
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    viewModel = viewModel
                ){query->
                    viewModel.searchBooks(query)

                }

                Spacer(modifier = Modifier.height(13.dp))

                BookList(navController)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean =false,
    hint:String = "Search",
    viewModel: SearchBookViewModelv2,
    onSearch: (String)->Unit = {}){

    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty()
    }
    Column{
        InputField(valueState =searchQueryState ,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions{
                if(!valid)return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })

    }
}

@Composable
fun BookList(
    navController: NavHostController,
    viewModel: SearchBookViewModelv2 = hiltViewModel(),
){

    val listOfBook = viewModel.list


    if(viewModel.isLoading){
        LinearProgressIndicator()
    }else{
        LazyColumn(
            modifier =Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ){
            items(listOfBook){ book->
                BookRow(book,navController)
            }
        }

    }



}

@Composable
fun BookRow(book: Item,
            navController: NavHostController) {


    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(ReaderScreens.DetailScreen.name+"/${book.id}")
            }
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
                if(book.volumeInfo.imageLinks.smallThumbnail.isEmpty() == true){
                    "https://img1.ak.crunchyroll.com/i/spire2/8a6279fc503fe64b451a0e63afc658281610403545_full.jpg"
                }
                else {
                book.volumeInfo.imageLinks.smallThumbnail
                 }
            Image(painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Anime IMage",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .padding(end = 4.dp))

            Column(){
                Text(text = book.volumeInfo.title.toString(),
                    overflow = TextOverflow.Ellipsis)

                Text(text = "Authors: ${book.volumeInfo.authors}",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)


                Text(text = "Date: ${book.volumeInfo.publishedDate}",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)


                Text(text = "Categories: ${book.volumeInfo.categories}",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.caption,
                    fontStyle = FontStyle.Italic)
            }
        }

    }

}
