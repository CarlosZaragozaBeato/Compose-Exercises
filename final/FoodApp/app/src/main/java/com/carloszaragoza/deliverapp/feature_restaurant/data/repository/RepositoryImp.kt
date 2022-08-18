package com.carloszaragoza.deliverapp.feature_restaurant.data.repository

import com.carloszaragoza.deliverapp.feature_restaurant.data.data_source.RecipesDao
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order
import com.carloszaragoza.deliverapp.feature_restaurant.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val dao: RecipesDao
): Repository {
    override fun getOrders(): Flow<List<Order>> {
       return dao.getRecipes()
    }

    override suspend fun insertOrder(order: Order) {
        dao.insertRecipe(order)
    }

    override suspend fun deleteOrder(id: Int?) {
        dao.deleteRecipe(id)
    }

    override suspend fun deleteAll() {
       dao.deleteAllOrders()
    }


    override suspend fun getOrderById(id: Int) {
       dao.getRecipeById(id)
    }

}