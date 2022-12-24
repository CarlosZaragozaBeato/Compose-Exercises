package com.carloszaragoza.groceryapp.feature_login.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImageCustom(
    modifier: Modifier,
    image:Int,
    contentDescription: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(image),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(.7f))
    }
}