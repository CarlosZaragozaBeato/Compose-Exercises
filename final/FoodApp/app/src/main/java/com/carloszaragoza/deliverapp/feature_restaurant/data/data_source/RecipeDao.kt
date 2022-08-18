package com.carloszaragoza.deliverapp.feature_restaurant.data.data_source

import androidx.room.*
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao{

    @Query("SELECT * FROM order_tbl")
    fun getRecipes(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(order: Order)

    @Query("DELETE FROM order_tbl WHERE id == :id")
    fun deleteRecipe(id: Int?)


    @Query("SELECT * FROM order_tbl WHERE id == :id")
    fun getRecipeById(id: Int):Order

    @Query("DELETE FROM order_tbl")
    fun deleteAllOrders()
}