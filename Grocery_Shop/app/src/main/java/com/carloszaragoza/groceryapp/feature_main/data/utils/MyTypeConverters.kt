package com.carloszaragoza.groceryapp.feature_main.data.utils

import androidx.room.TypeConverter
import com.carloszaragoza.groceryapp.feature_shop.domain.model.ItemsList
import com.carloszaragoza.groceryapp.feature_main.domain.model.OrderList
import com.google.gson.Gson

class MyTypeConverters {

    @TypeConverter
    fun fromItemToJSON(itemsList: ItemsList): String {
        return Gson().toJson(itemsList)
    }
    @TypeConverter
    fun fromJSONToItem(json: String): ItemsList {
        return Gson().fromJson(json, ItemsList::class.java)
    }
    @TypeConverter
    fun fromOrderToJSON(orderList: OrderList): String {
        return Gson().toJson(orderList)
    }
    @TypeConverter
    fun fromJSONToOrder(json: String): OrderList {
        return Gson().fromJson(json,OrderList::class.java)
    }
}