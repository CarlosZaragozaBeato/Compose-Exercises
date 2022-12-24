package com.carloszaragoza.groceryapp.feature_shop.data.data_source

import androidx.room.*
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import kotlinx.coroutines.flow.Flow


@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(order: Order)

    @Delete
    fun deleteOrder(order: Order)

    @Query("SELECT * FROM order_tbl where :id == id")
    fun getOrderById(id:Int?): Order?

    @Query("SELECT * FROM order_tbl")
    fun getOrders(): Flow<List<Order>>

    @Query("DELETE FROM order_tbl")
    fun deleteAllOrders()

}