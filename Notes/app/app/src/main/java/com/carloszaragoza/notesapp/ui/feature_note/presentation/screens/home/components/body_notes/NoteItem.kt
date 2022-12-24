package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.body_notes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.core.graphics.toColorLong
import com.carloszaragoza.notesapp.ui.feature_login.domain.model.notes_db.note_dbItem
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util.PriorityUtil
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing
import com.carloszaragoza.notesapp.ui.theme.listOfColor

@Composable
fun NoteItem(
    note: note_dbItem,
    onDeleteNote:(note_dbItem) -> Unit,
    onNavigate:(Int?) -> Unit
){

    Card(
        modifier = Modifier
            .clickable {
                onNavigate.invoke(note.id)
            }
            .fillMaxWidth()
            .padding(LocalSpacing.current.small),
        backgroundColor = Color(note.COLOR)
    ){
        Column(
            modifier = Modifier
                .padding(LocalSpacing.current.small),
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(note.TITLE,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xffEEEEEE),
                        fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.fillMaxWidth(.8f))

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(PriorityUtil.util(note.PRIORITY)))
                    )
                    IconButton(onClick = { onDeleteNote.invoke(note) }) {
                        Icon(imageVector = Icons.Default.Remove ,
                            contentDescription = "Delete Note",
                            tint = Color.White)
                    }
                }
            }

          note.CONTENT?.let {
              Text(it,
                  style = TextStyle(
                      color = Color(0xffEEEEEE).copy(.8f),
                  ))
          }
        }
    }
}
