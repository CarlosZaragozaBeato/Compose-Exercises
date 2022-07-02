package com.example.areader.screens.update

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.areader.data.DataOrException
import com.example.areader.model.MBook
import com.example.areader.screens.home.HomeViewModel
import com.example.areader.screens.home.components.ReaderAppBar
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.areader.R
import com.example.areader.components.InputField
import com.example.areader.navigation.ReaderScreens
import com.example.areader.screens.details.saveToFirebase
import com.example.areader.screens.home.components.RoundedButton
import com.example.areader.utils.formatDate
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UpdateScreen(
    navController: NavHostController,
    bookId: String,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Update Book",
            navController =navController,
            icon = Icons.Default.ArrowBack,
            showProfile = false){
            navController.popBackStack()
        }
    }) {

        val bookInfo = produceState<DataOrException<List<MBook>,
                                                    Boolean,
                                                    Exception>>(initialValue =
                                                    DataOrException(
                                                        data = emptyList(),
                                                        true,
                                                        Exception("")
                                                    )){
                                                        value = viewModel.data.value
        }.value

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if(bookInfo.loading == true){
                    LinearProgressIndicator()
                    bookInfo.loading = false
                }else{

                    Surface(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        shape = CircleShape,
                        elevation = 4.dp,
                    ){
                        ShowBookUpdate(bookInfo = viewModel.data.value,
                                        bookItemId = bookId)
                    }

              ShowSimpleForm(book = viewModel.data.value.data?.first{ mBook->
                  mBook.googleBookId == bookId
              }!!, navController = navController)

                }
                
            }

        }




    }


    
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ShowSimpleForm(book: MBook, navController: NavHostController) {

    val notesText = remember{
        mutableStateOf("")
    }

    val isStartedReading = remember{
        mutableStateOf(false)
    }
    val isFinishedReading = remember{
        mutableStateOf(false)
    }
    val ratingVal = remember{
        mutableStateOf(0)
    }

    val context = LocalContext.current


    SimpleForm(defaultValue = if(book.notes.toString().isNotEmpty()) book.notes.toString()
                                else "No Thoughts Available"){ note ->
        notesText.value = note
    }
    
    Row (
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
            ){
        TextButton(onClick = { isStartedReading.value = true },
            enabled =  book.startedReading == null) {

            if(book.startedReading == null){
                if(!isStartedReading.value){
                    Text(text = "Start Reading")
                }else{
                    Text("Started Reading",
                        modifier = Modifier
                            .alpha(0.6f),
                        color = Color.Red.copy(alpha = 0.5f))
                }
            }else{
                Text(text = "Started on ${formatDate(book.startedReading!!)}")
            }
            
            Spacer(modifier = Modifier.width(4.dp))

            TextButton(onClick = { isFinishedReading.value = true },
                enabled =  book.finishedReading == null) {

                if (book.finishedReading == null) {
                    if (!isFinishedReading.value) {
                        Text(text = "Mark as Read")
                    } else {
                        Text("Finished Reading!",
                            modifier = Modifier
                                .alpha(0.6f),
                            color = Color.Red.copy(alpha = 0.5f))
                    }
                } else {
                    Text(text = "Finished on ${formatDate(book.finishedReading!!)}")
                }
            }
        }

        }
    Text("Rating", modifier = Modifier.padding(bottom = 3.dp))
    book.rating?.toInt().let{ rating ->
        RatingBar(rating = rating!!){
            ratingVal.value = it
        }
    }
    Spacer(modifier = Modifier.height(50.dp))


    val changeNote = book.notes != notesText.value
    val changeRating = book.rating?.toInt() !=ratingVal.value
    val isFinishedTimeStamp =   if(isFinishedReading.value) Timestamp.now()
    else book.finishedReading
    val isStartedTimeStamp =    if(isStartedReading.value) Timestamp.now()
    else book.startedReading

    val bookUpdate = changeNote || changeRating || isFinishedReading.value || isStartedReading.value

    val bookToUpdate = hashMapOf(
        "finished_reading" to isFinishedTimeStamp,
        "started_reading" to isStartedTimeStamp,
        "rating" to ratingVal.value,
        "notes" to notesText.value).toMap()


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween){

        RoundedButton(label = "Update") {
            if(bookUpdate){
                FirebaseFirestore.getInstance()
                    .collection("books")
                    .document(book.id!!)
                    .update(bookToUpdate)
                    .addOnCompleteListener {
                        showToast(context, "Book Updated Successfully")
                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }
                    .addOnFailureListener {

                    }
            }
        }

        val openDialog = remember {
            mutableStateOf(false)
        }
        if(openDialog.value){
            ShowAlertDialog(message = stringResource(id = R.string.sure)+"\n"+
                            stringResource(id = R.string.action),
                            openDialog){FirebaseFirestore.getInstance()
                .collection("books")
                .document(book.id!!)
                .delete()
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        openDialog.value = false

                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }
                }

            }


        }
        RoundedButton(label = "Delete"){
            openDialog.value = true
        }
    }

}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RatingBar(
    modifier:Modifier = Modifier,
    rating: Int,
    onPressRating:(Int)-> Unit
){
    var ratingState by remember{
        mutableStateOf(rating)
    }

    var selected by remember{
        mutableStateOf(false)
    }
    val size by animateDpAsState(targetValue = if(selected) 42.dp else 34.dp,
        spring(Spring.DampingRatioHighBouncy))

    Row(
        modifier = Modifier.width(280.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        
        for (i in 1..5){
            Icon(painter = painterResource(id = R.drawable.estrella),
            contentDescription = "star Icon",
            modifier = Modifier
                .width(size)
                .height(size)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selected = true
                            onPressRating(i)
                            ratingState = i
                        }
                        MotionEvent.ACTION_UP -> {
                            selected = false
                        }
                    }
                    true
                },
                tint = if(i<=ratingState)Color(0xffffd700)
                        else Color(0xffa2adb1)
            )
        }
        
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleForm(
    modifier:Modifier = Modifier,
    loading: Boolean = false,
    defaultValue:String = "Great Book",
    onSearch:(String) -> Unit = {}
) {
    Column(){
        val textFieldValue = rememberSaveable{
            mutableStateOf(defaultValue)
        }
        val keyboardController = LocalSoftwareKeyboardController.current

        val valid = remember(textFieldValue.value){
            textFieldValue.value.trim().isNotEmpty()
        }

        InputField(
                    modifier = Modifier
                        .height(140.dp)
                        .padding(3.dp)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    valueState = textFieldValue,
                    labelId = "Enter Your Thoughts",
                    enabled = true,
                    onAction = KeyboardActions{
                        if(!valid) return@KeyboardActions

                        onSearch(textFieldValue.value.trim())
                        keyboardController?.hide()
                    })
    }


}

@Composable
fun ShowBookUpdate(bookInfo: DataOrException<List<MBook>, Boolean, Exception>,
                    bookItemId:String) {

    Row {
        Spacer(modifier =Modifier.width(43.dp))
        if(bookInfo.data!=null){
            Column(
                modifier = Modifier
                    .padding(4.dp),
                verticalArrangement = Arrangement.Center){
                CardListItem(book = bookInfo.data!!.first{mBook->
                    mBook.googleBookId == bookItemId
                },onPressDetails = {})
            }
        }
    }

}

@Composable
fun CardListItem(book: MBook, onPressDetails: () -> Unit) {

    Card(modifier = Modifier
        .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
        .clip(RoundedCornerShape(20.dp))
        .clickable {

        },
        elevation = 8.dp){

        Row(
            horizontalArrangement = Arrangement.Start
        ){
            Image(painter = rememberAsyncImagePainter(model = book.photoUr.toString()),
                contentDescription = "Image of a Book",
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(topStart = 120.dp,
                        topEnd = 20.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp)))

            Column {
                Text(text = book.title.toString(),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(120.dp),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)

                Text(text = book.authors.toString(),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(120.dp),
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)


                Text(text = book.publishedDate.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp,bottom =8.dp)
                )

            }
        }
    }
}

fun showToast(context: Context ,message:String){
    Toast.makeText(context, message,Toast.LENGTH_LONG).show()
}
@Composable
fun ShowAlertDialog(
    message: String,
    openDialog: MutableState<Boolean>,
    onYesPress:()->Unit) {

    if(openDialog.value){
        AlertDialog(onDismissRequest = { openDialog.value = false },
            title = {Text(text = "Delete Book")},
            text = { Text(text = message)},
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    TextButton(onClick = { onYesPress.invoke() }) {
                        Text(text = "Yes" )
                    }
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(text = "No" )
                    }
                }
            } )

        }


}