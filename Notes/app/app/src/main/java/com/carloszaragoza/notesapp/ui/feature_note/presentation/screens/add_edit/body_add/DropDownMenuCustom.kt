package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.add_edit.body_add

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.carloszaragoza.notesapp.ui.theme.LocalSpacing

@Composable
fun DropMenuCustom(
    currentValue:String,
    expanded: MutableState<Boolean>,
    itemsList:List<String>,
    onChangePriority:(String) -> Unit
){

    val textfieldSize = remember {
        mutableStateOf(Size.Zero)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(.5f)
            .padding(vertical = LocalSpacing.current.small,
                    horizontal = LocalSpacing.current.medium)
    ) {
        OutlinedTextField(

            value = currentValue,
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textfieldSize.value = coordinates.size.toSize()
                }
                .border(width = 1.dp, color = Color.White),
            enabled = false,
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown, "contentDescription",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable {
                            expanded.value = !expanded.value
                        }
                        .size(30.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledLabelColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )

        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.value.width.toDp() })
        ) {
            itemsList.forEach { priority ->
                DropdownMenuItem(onClick = {
                    onChangePriority.invoke(priority)
                    expanded.value = false
                }) {
                    Text(text = priority)
                }
            }
        }

    }
}