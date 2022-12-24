package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.components.app_bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material.icons.filled.ViewColumn
import androidx.compose.runtime.Composable

@Composable
fun IconAppBar(
    currentLayout: Boolean,
    onChangeLayout: () -> Unit
){

    IconButton(onClick = { onChangeLayout.invoke() }) {
        Icon(imageVector = if(currentLayout)
                                Icons.Default.ViewColumn
                            else
                                Icons.Default.TableRows ,
            contentDescription ="Change Layout",
            tint = MaterialTheme.colors.onBackground)
    }
}