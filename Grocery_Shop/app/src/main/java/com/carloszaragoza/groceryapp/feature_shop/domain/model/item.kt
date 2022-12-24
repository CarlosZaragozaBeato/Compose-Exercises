package com.carloszaragoza.groceryapp.feature_main.domain.model
import com.carloszaragoza.groceryapp.R

class Item (
    val id:String,
    val name:String,
    val price:Double,
    val weight: Double,
    val category:String,
    val image:Int
){
    companion object{
        val listOfIngredients:List<Item> = listOf(
            Item(
                id = "m1" ,
                name = "Chicken",
                price = 7.5,
                weight = 1.0,
                category = "c1",
                image = R.drawable.meat_chicken
            ),
            Item(
                id = "m2",
                name = "Turkey",
                price = 4.0,
                weight = 1.0,
                category = "c1",
                image = R.drawable.meat_turkey
            ),
            Item(
                id = "f1" ,
                name = "Salmon",
                price = 16.99,
                weight = 1.0,
                category = "c2",
                image = R.drawable.fish_salmon
            ),
            Item(
                id = "f2" ,
                name = "Cod" ,
                price = 14.25,
                weight = 1.0,
                category = "c2",
                image = R.drawable.fish_cod
            ),
            Item(
                id = "v1" ,
                name = "Leafy Green",
                price = 2.0,
                weight = 1.0,
                category = "c3",
                image = R.drawable.vegetables_leafy_green
            ),

            Item(
                id = "v2" ,
                name = "Brocoli",
                price = 4.0,
                weight = 1.0,
                category = "c3",
                image = R.drawable.vegetables_brocoli
            ),
            Item(
                id = "h1",
                name = "Allspice",
                price = 1.5,
                weight = 1.0,
                category = "c4",
                image = R.drawable.spicies_allspice
            ),
            Item(
                id = "h2",
                name = "Anise",
                price = 2.0,
                weight = 1.0,
                category = "c4",
                image = R.drawable.spicies_anise
            ),
            Item(
                id = "p1",
                name = "Pappardelle",
                price = 2.0,
                weight = 1.0,
                category = "c5",
                image = R.drawable.pasta_pappardelle
            ),
            Item(
                id = "p2",
                name = "Rice",
                price = 1.0,
                weight = 1.0,
                category = "c5",
                image = R.drawable.pasta_rice
            ),
            Item(
                id = "fr1",
                name = "Apple",
                price = 3.0,
                weight = 1.0,
                category = "c6",
                image =R.drawable.fruits_apple
            ),
            Item(
                id = "fr2",
                name = "Raspberries",
                price = 2.0,
                weight = 1.0,
                category = "c6",
                image =R.drawable.fruits_raspberries
            )
        )
    }
}
