package com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category

import com.carloszaragoza.deliverapp.R

class Category(
    val id: String,
    val name:String,
    val image:Int,
    val recipes: List<String>){

    companion object {
        val listOfCategories:List<Category> = listOf(
            Category(
                id = "c1",
                name = "Ramen",
                image = R.drawable.ramen_category,
                recipes = listOf(
                    "r1",
                    "r2",
                    "r3"
                )
            ),
            Category(
                id = "c2",
                name = "Burger",
                image = R.drawable.burger_category,
                recipes = listOf(
                    "b1",
                    "b2"
                )
            ),
            Category(
                id = "c3",
                name = "Salad",
                image = R.drawable.salad_category,
                recipes = listOf(
                    "s1",
                    "s2",
                    "s3"
                )
            ),
            Category(
                id = "c4",
                name = "Waffle",
                image = R.drawable.waffle_category,
                recipes = listOf(
                    "w1",
                    "w2"
                )
            ),
            Category(
                id = "c5",
                name = "Pizza",
                image = R.drawable.pizza_category,
                recipes = listOf(
                    "p1",
                    "p2",
                    "p3"
                )
            ),
            Category(
                id = "c6",
                name = "Sushi",
                image = R.drawable.sushi_category,
                recipes = listOf(
                    "sh1",
                    "sh2",
                    "sh3"
                )
            ),
            Category(
                id = "c7",
                name = "Cakes",
                image = R.drawable.cake_category,
                recipes = listOf(
                    "ck1",
                    "ck2",
                    "ck3"
                )
            ),
            Category(
                id = "c8",
                name = "Sandwiches",
                image = R.drawable.sandwich_category,
                recipes = listOf(
                    "sw1",
                    "sw2",
                    "sw3"
                )
            )


        )
    }

}
