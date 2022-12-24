package com.carloszaragoza.groceryapp.feature_main.presentation.util

import com.carloszaragoza.groceryapp.R

sealed class OnBoardingPage(
    val image:Int,
    val title:String,
    val description:String
){
    object First:OnBoardingPage(
        title = "Grab your Delicious Ingredients!",
        description = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto..",
        image = R.drawable.food_purchase_bag
    )
    object Second:OnBoardingPage(
        title = "Free delivery!",
        description  = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.",
        image = R.drawable.delivery
    )
    object Third:OnBoardingPage(
        title = "Start your Search!",
        description  = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.",
        image = R.drawable.ingredients
    )

}