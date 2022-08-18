package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlos_zaragoza.noteapp.feature_note.domain.model.Note
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteItem(
    note: Note,
    modifier:Modifier = Modifier,
    background:Color,
    onEvent: (NotesEvents) -> Unit,

){
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(CornerSize(10.dp)),
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        backgroundColor = Color(note.color)

    ){
        Column(
            modifier = Modifier
                .padding(top = 10.dp,bottom = 10.dp, start =15.dp,),

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(.8f)
            ) {
                Text(
                    note.title,
                    style = TextStyle(
                        color = Color(0xffE2DCC8),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Card(
                    modifier = Modifier
                        .size(8.dp),
                    shape = CircleShape,
                    backgroundColor = background
                ) {

                }

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                note.description?.let { description ->
                    Text(
                        note.description.toString(), style = TextStyle(
                            color = Color(0xffC4D7E0),
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.8f)
                    )
                }
            }
        }
    }
}