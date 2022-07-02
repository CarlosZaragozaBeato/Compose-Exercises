package com.example.areader.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.areader.model.MBook
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderAppBar(
    title:String,
    showProfile: Boolean = true,
    navController: NavHostController,
    icon:ImageVector? = null,
    onBackArrowClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                if(showProfile){
                    Icon(imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f))
                }

                if(icon!= null){
                    Icon(imageVector = icon ,
                        contentDescription = "Arrow Back",
                        tint = Color.Red.copy(alpha = .7f),
                        modifier = Modifier
                            .clickable { onBackArrowClick.invoke() })
                }

                Spacer(modifier = Modifier.width(50.dp))

                Text(text = title,
                    color = Color.Red.copy(alpha = .5f),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ))




            }
        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {
                if(showProfile){
                    Row() {
                        Icon(imageVector =  Icons.Filled.Logout,
                            contentDescription = "Logout",
                            tint = Color.Green.copy(alpha= .4f))
                    }
                }else{
                    Box{}
                }


            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp

    )
}



@Composable
fun TitleSection(
    modifier:Modifier= Modifier,
    label:String) {
    Surface(
        modifier = modifier
            .padding(start = 5.dp, top = 1.dp)){
        Column() {
            Text(text = label,
                fontSize = 19.sp,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Left)
        }

    }

}


@Composable
fun FabContent(onTap: () -> Unit) {
    FloatingActionButton(
        onClick = { onTap() },
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xff92cbdf)
    ){
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = Color(0xffEEEEEE))
    }
}








@Composable
fun ReadingRightNowArea(
    listOfBooks:List<MBook>,
    navController: NavHostController) {

    val readingNowList = listOfBooks.filter{ mBook->
        mBook.startedReading !=null && mBook.finishedReading == null
    }

    HorizontalScrollableComponent(readingNowList){
        navController.navigate(ReaderScreens.UpdateScreen.name+"/$it")
    }

}



@Composable
fun BookListArea(
    listOfBooks: List<MBook>,
    navController : NavHostController,
) {

    val addedBook = listOfBooks.filter{ mBook->
        mBook.startedReading == null && mBook.finishedReading == null
    }

    HorizontalScrollableComponent(addedBook){
        navController.navigate(ReaderScreens.UpdateScreen.name+"/$it")
    }

}

@Composable
fun HorizontalScrollableComponent(
    listOfBooks: List<MBook>,
    viewModel: HomeViewModel = hiltViewModel(),
    onCardPressed: (String) -> Unit,
) {

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .horizontalScroll(scrollState)
    ){
        if(viewModel.data.value.loading == true){
            LinearProgressIndicator()
        }else{
            if (listOfBooks.isNullOrEmpty()){
                Surface(modifier = Modifier
                    .padding(23.dp)) {
                    Text(text = "No books found. Add a Book",
                        style = TextStyle(
                            color = Color.Red.copy(alpha= 0.4f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ))
                }
            }else{
                for(book in listOfBooks){
                    ListCard(book){
                        onCardPressed(book.googleBookId.toString())
                    }
                }
            }

        }

    }


}










@Composable
fun ListCard(book: MBook ,
            onPressDetails: (String) -> Unit = {}) {

    val context = LocalContext.current
    val resources = context.resources

    val displayMetrics = resources.displayMetrics

    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    val spacing = 10.dp


    Card(
        shape = RoundedCornerShape(29.dp),
        backgroundColor = Color.White,
        elevation = 6.dp,
        modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable {
                onPressDetails.invoke(book.title.toString())
            },


        ) {
        Column(
            modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
            horizontalAlignment = Alignment.Start
        ) {

            Row(horizontalArrangement = Arrangement.Center) {
                Image(painter = rememberAsyncImagePainter(book.photoUr.toString()),
                    contentDescription = "Image Painter",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(50.dp))

                Column(
                    modifier = Modifier.padding(top = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier.padding(bottom = 1.dp))

                    BookRating(score = book.rating!!)


                }

            }

            Text(text = book.title.toString(),
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Text(text = book.authors.toString(),
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.caption)




            }
        val isStartedReading = remember{
            mutableStateOf(false)
        }
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            isStartedReading.value = book.startedReading != null

            RoundedButton(label = if(isStartedReading.value){"Reading"}
                                    else{ "Not yet"}, radius = 70) {

            }
        }


    }
}
@Composable
fun BookRating(score: Double = 4.5) {


    Surface(modifier = Modifier
        .height(70.dp)
        .padding(4.dp),
        shape = RoundedCornerShape(56.dp),
        elevation = 6.dp,
        color = Color.White) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {

            Icon(imageVector = Icons.Filled.StarBorder,
                contentDescription = "Star Icon",
                modifier = Modifier.padding(3.dp))
            Text(text = score.toString(),
                style = MaterialTheme.typography.subtitle1)
        }
    }
}

@Composable
fun RoundedButton(
    label:String,
    radius: Int = 29,
    onPress:() -> Unit
){
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEndPercent = radius ,
                topStartPercent =radius )),
        color = Color(0xff92cbdf)
    ){
        Column(
            modifier = Modifier
                .width(90.dp)
                .heightIn(40.dp)
                .clickable {
                    onPress.invoke()
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = label,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp
                ))
        }


    }


}








