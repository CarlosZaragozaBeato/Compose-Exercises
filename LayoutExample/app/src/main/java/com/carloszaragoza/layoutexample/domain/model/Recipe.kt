package com.carloszaragoza.layoutexample.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.carloszaragoza.layoutexample.R

data class Recipe(
    val id:String,
    val name:String,
    val short_name:String,
    val preparation:String,
    val image:Int,
    val description:String,
    val properties:List<MealPrp>) {

    companion object{
        val list_of_reciper = listOf<Recipe>(
            Recipe(
                id = "rcp1",
                name = "Smoked Salmon & Avocado Toast",
                short_name = "Salmon Toasts",
                preparation = "Easy",
                description = "This open bagel and salmon sandwich combines avocado and smoked salmon, slashed with tomato",
                image = R.drawable.slmn,
                properties =  listOf<MealPrp>(
                    MealPrp("20",12.0, "Proteins", Color(0xff67BFB2).toArgb()),
                    MealPrp("67",20.0, "Carbs", Color(0xffE08E2C).toArgb()),
                    MealPrp("32",81.0, "Fats", Color(0xff3977AF).toArgb()),
                    MealPrp("598",0.0, "Calories", Color(0xff3977AF).toArgb()),
            ),
            ),
            Recipe(
                id = "rcp2",
                name = "Smoked Salmon & Avocado Toast",
                short_name = "Salmon Toasts 2",
                preparation = "Easy",
                description = "This open bagel and salmon sandwich combines avocado and smoked salmon, slashed with tomato",
                image = R.drawable.salmon,
                properties =  listOf<MealPrp>(
                    MealPrp("20",12.0, "Proteins", Color(0xff67BFB2).toArgb()),
                    MealPrp("67",20.0, "Carbs", Color(0xffE08E2C).toArgb()),
                    MealPrp("32",81.0, "Fats", Color(0xff3977AF).toArgb()),
                    MealPrp("598",0.0, "Calories", Color(0xff3977AF).toArgb())),
                    ),
            Recipe(
                id = "rcp3",
                name = "Smoked Salmon & Avocado Toast",
                short_name = "Salmon Toasts 3",
                preparation = "Easy",
                description = "This open bagel and salmon sandwich combines avocado and smoked salmon, slashed with tomato",
                image = R.drawable.slmn,
                properties =  listOf<MealPrp>(
                    MealPrp("20",12.0, "Proteins", Color(0xff67BFB2).toArgb()),
                    MealPrp("67",20.0, "Carbs", Color(0xffE08E2C).toArgb()),
                    MealPrp("32",81.0, "Fats", Color(0xff3977AF).toArgb()),
                    MealPrp("598",0.0, "Calories", Color(0xff3977AF).toArgb()),),),
            Recipe(
                id = "rcp4",
                name = "Smoked Salmon & Avocado Toast",
                short_name = "Salmon Toasts 4",
                preparation = "Easy",
                description = "This open bagel and salmon sandwich combines avocado and smoked salmon, slashed with tomato",
                image = R.drawable.slmn,
                properties =  listOf<MealPrp>(
                    MealPrp("20",12.0, "Proteins", Color(0xff67BFB2).toArgb()),
                    MealPrp("67",20.0, "Carbs", Color(0xffE08E2C).toArgb()),
                    MealPrp("32",81.0, "Fats", Color(0xff3977AF).toArgb()),
                    MealPrp("598",0.0, "Calories", Color(0xff3977AF).toArgb())),
            ))

    }
}