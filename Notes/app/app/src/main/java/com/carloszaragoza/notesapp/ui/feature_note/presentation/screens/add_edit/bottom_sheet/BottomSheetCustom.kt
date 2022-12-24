package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.bottom_sheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.AddEditNotesViewModel
import com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.util.AddEditEvents
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing
import com.carloszaragoza.notesapp.ui.theme.listOfColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetCustom(
    viewModel: AddEditNotesViewModel = hiltViewModel(),
    sheetState: BottomSheetState
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ){
            IconButton(onClick = {
                scope.launch {
                    sheetState.collapse()
                }
            },
                modifier = Modifier
                    .padding(start = LocalSpacing.current.small)) {
                Icon(imageVector = Icons.Default.Palette,
                    contentDescription = "Colors",
                    tint = Color.Black)
            }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(start = LocalSpacing.current.medium)
            ){
                items(listOfColor){ color ->
                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .padding(LocalSpacing.current.small)
                            .clickable{
                                      viewModel.onEvent(AddEditEvents.OnColorChange(color.toArgb()))
                            },
                        backgroundColor = color,
                        shape = CircleShape,
                        border = BorderStroke(width =2.dp,
                                                color = if(state.color.value == color.toArgb())
                                                            Color.LightGray
                                                        else
                                                            Color.Transparent),
                        elevation = if(state.color.value == color.toArgb())
                                        4.dp
                                    else
                                        0.dp

                        ){}
                }
            }
        }
    }
    
}