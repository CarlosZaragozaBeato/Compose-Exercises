package com.carloszaragoza.deliverapp.feature_restaurant.presentation.util
import com.carloszaragoza.deliverapp.R

sealed class OnboardingPage(
    val image:Int,
    val title:String,
    val description:String
){
  object First:OnboardingPage(
      title = "Grab your Delicious Food!",
      description = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto..",
      image = R.drawable.ramen_image
  )
  object Second:OnboardingPage(
        title = "Free delivery!",
        description  = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.",
        image = R.drawable.delivery_food
    )
  object Third:OnboardingPage(
        title = "Start your Search!",
        description  = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto.",
        image = R.drawable.food_search
    )


}
