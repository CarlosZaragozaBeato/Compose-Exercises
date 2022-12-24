package com.carloszaragoza.groceryapp.feature_shop.presentation.user.components.user_appbar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserAppBar(
    username:String,
    onPop:()->Unit,
    onLogOut:() -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { onPop.invoke() }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    tint = MaterialTheme.colors.onBackground)
            }
        },
        title = {
            Text(username,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onBackground)
        },
        actions = {
            TextButton(onClick = { onLogOut.invoke() }) {
                Text("LogOut",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red.copy(.5f),
                    fontSize = MaterialTheme.typography.caption.fontSize)
            }
        }
    )
}