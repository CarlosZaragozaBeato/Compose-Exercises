package com.carloszaragoza.groceryapp.feature_main.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.carloszaragoza.groceryapp.feature_login.data.data_source.LoginDao
import com.carloszaragoza.groceryapp.feature_main.data.utils.MyTypeConverters
import com.carloszaragoza.groceryapp.feature_shop.domain.model.Order
import com.carloszaragoza.groceryapp.feature_main.domain.model.User
import com.carloszaragoza.groceryapp.feature_shop.data.data_source.OrdersDao

@TypeConverters(value =  [MyTypeConverters::class])
@Database(
    entities = [User::class, Order::class],
    version = 5
)
abstract class ShopDatabase:RoomDatabase() {
    abstract val loginDao : LoginDao
    abstract val orderDao : OrdersDao

    companion object{
        const val DATABASE_NAME = "shop_db"
    }
}