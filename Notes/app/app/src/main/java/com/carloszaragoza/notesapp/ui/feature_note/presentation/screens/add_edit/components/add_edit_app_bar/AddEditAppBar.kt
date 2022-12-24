package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.components.add_edit_app_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing

@Composable
fun AddEditAppBar(
    onPop:() -> Unit,
    onSave:() -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LocalSpacing.current.small),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(onClick = { onPop.invoke()}) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription ="Go to home page.",
                    tint = Color.White)
            }
            IconButton(onClick = { onSave.invoke() }) {
                Icon(imageVector = Icons.Default.Save,
                    contentDescription ="Save Note.",
                    tint = Color.White)
            }
        }
    }
}