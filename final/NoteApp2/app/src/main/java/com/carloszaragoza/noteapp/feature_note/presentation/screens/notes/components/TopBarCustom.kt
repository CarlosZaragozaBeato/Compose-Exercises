package com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.NotesViewModel
import com.carlos_zaragoza.noteapp.feature_note.presentation.screens.notes.util.NotesEvents

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopBarCustom(
value:String,
onFilterChange: (String) -> Unit,
onChangeLayout: () -> Unit,
changeLayout: Boolean
){
    TopAppBar(
        modifier = Modifier
            .padding(vertical = 20.dp),
        title = {
            BasicTextField(value = value,
                onValueChange = {
                    onFilterChange(it)
                },
                textStyle = TextStyle(
                    color = Color.White
                ),
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(),
                modifier = Modifier
                    .background(Color.LightGray, shape = CircleShape)
                    .padding(10.dp)
                    .fillMaxWidth(.8f)
            )

        },
        actions = {
            IconButton(onClick = {onChangeLayout.invoke()}) {
                Icon(imageVector = if(changeLayout) Icons.Default.ViewColumn
                else Icons.Default.TableRows,
                    contentDescription = "Columns",
                    tint = Color.LightGray)
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}