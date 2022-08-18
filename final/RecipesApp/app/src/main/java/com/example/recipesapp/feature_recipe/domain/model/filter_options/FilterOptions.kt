package com.example.recipesapp.feature_recipe.domain.model.filter_options

import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filter_table")
data class FilterOptions(

    @PrimaryKey
    val title:String,
    val description:String,
    var check: Boolean
)
