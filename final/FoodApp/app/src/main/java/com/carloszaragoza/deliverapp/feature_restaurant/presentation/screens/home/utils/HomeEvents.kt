package com.carloszaragoza.deliverapp.feature_restaurant.presentation.screens.home.utils

import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.category.Category
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.location.Location
import com.carloszaragoza.deliverapp.feature_restaurant.domain.model.order.Order

sealed class HomeEvents{
    data class OnChangeLocation(val location: Location): HomeEvents()
    object FilterList: HomeEvents()
    data class OnChangeCategory(val category: Category): HomeEvents()
    object FilterRecipe: HomeEvents()
    data class OnAddOrder(val order: Order):HomeEvents()
    data class onNavigate(val route: String):HomeEvents()
}
