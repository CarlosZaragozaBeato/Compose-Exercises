package com.carloszaragoza.groceryapp.feature_shop.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.carloszaragoza.groceryapp.R
import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

class Category(
    val id:String,
    val name:String,
    val image:Int,
    val color:Int,
    val bgImage:Int,
    val listOfItems: List<Item>){


    companion object{
       val listOfCategories:List<Category> = listOf(
          Category(
             id = "c1",
             name = "Meat",
             image = R.drawable.meat_main,
             color = Color(0xfffcdcdb).toArgb(),
             bgImage = R.drawable.meat_bg,
             listOfItems =  emptyList()
          ),
          Category(
             id = "c2",
             name = "Fish",
             image = R.drawable.fish_main,
             color = Color(0xffB1E1FF).toArgb(),
             bgImage = R.drawable.fish_bg,
             listOfItems =  emptyList()
          ), Category(
             id = "c3",
             name = "Vegetables",
             image = R.drawable.vegetables_main,
             color = Color(0xffB1D7B4).toArgb(),
             bgImage = R.drawable.vegetables_bg,
             listOfItems =  emptyList()
          ), Category(
             id = "c4",
             name = "Herbs and Spicies",
             image = R.drawable.herbs_and_spicies_main,
             color = Color(0xff94B49F).toArgb(),
             bgImage = R.drawable.herbs_and_spicies_bg,
             listOfItems =  emptyList()
          ), Category(
             id = "c5",
             name = "Pasta, rice and pulses",
             image = R.drawable.pasta_rice_and_pulses_bg,
             color = Color(0xffF7E2D6).toArgb(),
             bgImage = R.drawable.pasta_rice_and_pulses_bg,
             listOfItems =  emptyList()
          ), Category(
             id = "c6",
             name = "Fruits",
             image = R.drawable.fruits_bg,
             color = Color(0xffFFCCB3).toArgb(),
             bgImage = R.drawable.fruits_main,
             listOfItems =  emptyList()
          )
       )
    }
 }
