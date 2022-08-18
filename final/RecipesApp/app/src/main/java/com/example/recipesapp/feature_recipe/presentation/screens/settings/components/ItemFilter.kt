package com.example.recipesapp.feature_recipe.presentation.screens.settings.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipesapp.feature_recipe.presentation.util.Colors
import com.example.recipesapp.feature_recipe.presentation.view_models.ViewModelMain

@Composable
fun ItemFilter(
    title:String,
    description:String,
    check: MutableState<Boolean?>,
    viewModelMain: ViewModelMain = hiltViewModel(),
    onChange: (Boolean) -> Unit = {}
){



    Row(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween){
        Column() {
            Text(title, style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            )

            Text(description, style = TextStyle(
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )
            )

        }
        Switch(checked = check.value!!,
            onCheckedChange = {
                check.value = it


            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Colors.redPastel,
                checkedTrackColor = Colors.lightBrownPastel
            ))
    }
}