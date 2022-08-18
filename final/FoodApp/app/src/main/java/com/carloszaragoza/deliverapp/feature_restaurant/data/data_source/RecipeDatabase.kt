package com.carloszaragoza.deliverapp.feature_restaurant.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order

@Database(
    entities = [Order::class],
    version = 4
)
abstract class RecipeDatabase: RoomDatabase() {
    abstract val recipeDao: RecipesDao

    companion object {
        const val DATABASE_NAME = "recipe_db"
    }
}