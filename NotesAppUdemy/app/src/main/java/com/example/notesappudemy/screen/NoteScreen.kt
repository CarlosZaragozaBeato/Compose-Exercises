package com.example.notesappudemy.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesappudemy.R
import com.example.notesappudemy.components.ButtonWidget
import com.example.notesappudemy.components.TextFieldNote
import com.example.notesappudemy.data.NoteData
import com.example.notesappudemy.model.Note
import com.example.notesappudemy.util.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes : List<Note>,
    onAddNote:(Note) -> Unit,
    RemoveNote:(Note) -> Unit
){

    var titleState by remember{
        mutableStateOf("")
    }
    var descriptionState by remember{
        mutableStateOf("")
    }

    var context  = LocalContext.current


        Column(
            modifier = Modifier
                .padding(6.dp)
        ) {
            TopAppBar(title = {
                              Text(text = stringResource(id = R.string.app_name),
                                   color = Color(0xffF9F2ED))
            }, 
                        actions = {
                            Icon(imageVector = Icons.Rounded.Notifications ,
                                contentDescription = "Notification Icon",
                                tint = Color(0xffF9F2ED))
                        },

                        backgroundColor = Color(0xff816797)
                )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){


                Spacer(modifier = Modifier.height(15.dp))
                TextFieldNote(
                                text =titleState,
                                label ="Title" ,
                                onTextChange = { title ->
                                 if(title.all{ char ->
                                        char.isLetter() || char.isWhitespace()
                                     }){
                                     titleState = title
                                 }
                                })
                TextFieldNote(
                                text =descriptionState,
                                label ="Description" ,
                                onTextChange = { description ->
                                    if(description.all{ char ->
                                            char.isLetter() || char.isWhitespace()
                                        }){
                                        descriptionState = description
                                    }
                                },
                                maxLine = 5)

                Spacer(modifier = Modifier.height(25.dp))
                ButtonWidget(text = "Add Note" ,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xff414141),
                            ) ,
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 10.dp,
                            ),
                            border = BorderStroke(2.dp,Color(0xffEEEEEE)),
                            textColor =  0xffF9F9F9,
                            modifier = Modifier
                                .width(200.dp),
                            OnClick = {
                                if(titleState.isNotEmpty() && descriptionState.isNotEmpty()){

                                    onAddNote(Note(
                                        title = titleState,
                                        description = descriptionState
                                    ))


                                    titleState = ""
                                    descriptionState = ""

                                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show();
                                }
                            }
                )
                Divider(
                    modifier = Modifier
                        .padding(10.dp))
                LazyColumn{
                    items(notes){ note ->
                        NoteCard(
                                note = note,
                                onNoteClicked = { nt ->
                                    RemoveNote(nt)
                                }
                        )


                    }
                }
            }

            
        }

}

@SuppressLint("NewApi")
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note:Note,
    onNoteClicked:(Note) -> Unit,
){
    Surface(
        modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp,
                bottomStart = 33.dp))
            .fillMaxWidth()

        ,
            color = Color(0xff414141),
            elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .clickable {onNoteClicked(note)}
                .padding(horizontal = 14.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.Start,
        ) {
            Text(text = note.title,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color(0xFFEEEEEE))



                Text(text = formatDate(note.entryTime.time),
                    style = MaterialTheme.typography.caption,
                    color = Color(0xCCEEEEEE))

            Spacer(modifier = Modifier
                .height(15.dp))
            Text(text = note.description,
                    style = MaterialTheme.typography.caption,
                    color = Color(0xD9EEEEEE))

        }
    }
}

