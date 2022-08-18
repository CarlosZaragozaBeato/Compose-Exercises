package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.location.Location

@Composable
fun DropMenuCustom(
    expanded:MutableState<Boolean>,
    itemsList:List<Location>,
    textfieldSize: MutableState<Size>,
    onChangeLocation:(Location) -> Unit
){
    Column {
        OutlinedTextField(

            value = "Your Location",
            onValueChange = {},
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    textfieldSize.value = coordinates.size.toSize()
                        }
                .width(170.dp),
            enabled = false,
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown, "contentDescription",
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .clickable{
                            expanded.value = !expanded.value
                        }
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                disabledIndicatorColor =Color.Transparent,
                disabledLabelColor = Color.Transparent,
                ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colors.primaryVariant
            )

        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.value.width.toDp() })
        ) {
            itemsList.forEach { location ->
                DropdownMenuItem(onClick = {
                    onChangeLocation.invoke(location)
                    expanded.value = false
                }) {
                    Text(text = location.city)
                }
            }
        }
    }
}
