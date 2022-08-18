package com.carloszaragoza.deliverapp.feature_restaurant.domain.model.recipe

import com.carloszaragoza.deliverapp.R


class Recipe(
    val id:String,
    val name:String,
    val rating:Double,
    val price:Double,
    val image:Int,

){
    companion object{
        val listOfRecipes:List<Recipe> = listOf(
            Recipe(
                id = "r1",
                name = "Sapporo Ramen",
                image = R.drawable.sapporo_ramen,
                price = 15.99,
                rating = 4.75
            ),
            Recipe(
                id = "r2",
                name = "Kurume Ramen",
                image = R.drawable.kurume_ramen,
                price = 10.99,
                rating = 4.25
            ),
            Recipe(
                id = "r3",
                name = "Okinawa Soba",
                image = R.drawable.okinawa_soba,
                price = 15.99,
                rating = 4.7
            ),
            Recipe(
                id = "b1",
                name = "Black Bean Burger",
                image = R.drawable.black_bean_burger,
                price = 19.99,
                rating = 4.8
            ),
            Recipe(
                id = "b2",
                name = "CheeseBurger",
                image = R.drawable.cheeseburger,
                price = 14.99,
                rating = 4.25
            ),
            Recipe(
                id = "s1",
                name = "Creamy Vegan Pasta Salad",
                image = R.drawable.creamy_vegan_salad,
                price = 11.99,
                rating = 4.4
            ),
            Recipe(
                id = "s2",
                name = "Greek Salad",
                image = R.drawable.greek_salad,
                price = 9.99,
                rating = 4.3
            ),
            Recipe(
                id = "s3",
                name = "Mexican Street Corn Salad",
                image = R.drawable.mexican_street_corn_salad,
                price = 12.99,
                rating = 4.6
            ),
            Recipe(
                id = "w1",
                name = "Crunchy Brussels Waffle",
                image = R.drawable.crunchy_brussels_waffle,
                price = 9.99,
                rating = 4.8
            ),
            Recipe(
                id = "w2",
                name = "Belgian Style Waffle",
                image = R.drawable.belgian_style_waffle,
                price = 11.99,
                rating = 4.4
            ),
            Recipe(
                id = "p1",
                name = "Cheese Pizza",
                image = R.drawable.cheesepizza,
                price = 9.99,
                rating = 4.25
            ),
            Recipe(
                id = "p2",
                name = "Supreme Pizza",
                image = R.drawable.supreme_pizza,
                price = 11.99,
                rating = 4.55
            ),
            Recipe(
                id = "p3",
                name = "BBQ Chicken Pizza",
                image = R.drawable.sapporo_ramen,
                price = 14.99,
                rating = 4.75
            ),
            Recipe(
                id = "sh1",
                name = "Sashimi",
                image = R.drawable.sashimi,
                price = 11.99,
                rating = 4.4
            ),
            Recipe(
                id = "sh2",
                name = "Nigiri",
                image = R.drawable.nigiri,
                price = 14.99,
                rating = 4.65
            ),
            Recipe(
                id = "sh3",
                name = "Temaki",
                image = R.drawable.temaki,
                price = 9.99,
                rating = 4.8
            ),
            Recipe(
                id = "ck1",
                name = "Angel Food Cake",
                image = R.drawable.angel_food_cake,
                price = 9.9,
                rating = 4.0
            ),
            Recipe(
                id = "ck2",
                name = "Cheese Cake",
                image = R.drawable.cheese_cake,
                price = 11.99,
                rating = 4.9
            ),
            Recipe(
                id = "ck3",
                name = "Devils Food Cake",
                image = R.drawable.devils_food_cake,
                price = 14.99,
                rating = 4.8
            ),
            Recipe(
                id = "sw1",
                name = "Chicken Sandwich",
                image = R.drawable.chicken_sandwich,
                price = 8.99,
                rating = 4.25
            ),
            Recipe(
                id = "sw2",
                name = "Seafood Sandwich",
                image = R.drawable.seafood_sandwich,
                price = 14.99,
                rating = 4.15
            ),
            Recipe(
                id = "sw3",
                name = "Roast Beef Sandwich",
                image = R.drawable.roast_beef_sandwich,
                price = 16.99,
                rating = 4.65
            ),
        )
    }
}
