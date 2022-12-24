package com.carloszaragoza.groceryapp.feature_shop.domain.model

import com.carloszaragoza.groceryapp.feature_main.domain.model.Item

data class ItemsList(
    var itemsList:MutableList<Item>
)
