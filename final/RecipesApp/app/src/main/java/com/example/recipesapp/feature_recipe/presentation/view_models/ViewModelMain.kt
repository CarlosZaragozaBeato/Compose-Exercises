package com.example.recipesapp.feature_recipe.presentation.view_models

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.LocalRecipesapp.feature__root_ide_package_.com.example.recipesapp.feature_recipe.domain.model.recipe.LocalRecipe.domain.util.DUMMY_CATEGORIES
import com.example.LocalRecipesapp.feature__root_ide_package_.com.example.recipesapp.feature_recipe.domain.model.recipe.LocalRecipe.domain.util.DUMMY_LocalRecipeS
import com.example.recipesapp.feature_recipe.domain.model.category.Category
import com.example.recipesapp.feature_recipe.domain.model.filter_options.FilterOptions
import com.example.recipesapp.feature_recipe.domain.model.recipe.LocalRecipe
import com.example.recipesapp.feature_recipe.domain.model.recipe.Recipe
import com.example.recipesapp.feature_recipe.domain.use_case.RecipeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val useCases: RecipeUseCases
):ViewModel() {

    private val _listCategories = mutableStateOf<List<Category>>(DUMMY_CATEGORIES)
    val listCategories: State<List<Category>> = _listCategories

    private val _listLocalRecipes = MutableStateFlow<List<LocalRecipe>>(DUMMY_LocalRecipeS)

    private val _listFilterLocalRecipes = MutableStateFlow<List<LocalRecipe>?>(null)
    val listFilterLocalRecipes = _listFilterLocalRecipes.asStateFlow()

    private val _meal = MutableStateFlow<List<LocalRecipe>?>(null)
    val meal = _meal.asStateFlow()


    private val _listOfFavorites = MutableStateFlow<List<Recipe>?>(null)

    private val _listOfOptions = MutableStateFlow<List<FilterOptions>?>(null)
    val listOfOptions = _listOfOptions.asStateFlow()

    val list = listOf(
        FilterOptions("Gluten-free","Only include gluten-free meals", false),
        FilterOptions("Lactose-free","Only include lactose-free meals",false),
        FilterOptions("Vegetarian","Only include vegetarian meals", false),
        FilterOptions("Vegan","Only include vegan meals",false,))

    fun getFilters()= viewModelScope.launch() {
        useCases.getFilter().distinctUntilChanged()
            .collect{
                _listOfOptions.value = it
            }
    }

    init{
        viewModelScope.launch {
            getFilters()
            delay(1000)
            if(_listOfOptions.value.isNullOrEmpty()){
                addFilter()
                getFilters()
            }
        }





    }


    fun addFilter() = viewModelScope.launch {
        list.forEach{
            useCases.addFilter(it)
        }
    }

    fun updateFilters(list:List<FilterOptions>)= viewModelScope.launch {
        list.forEach { item ->
            useCases.updateFilter(item)
        }
    }

    fun filterCategory(id:String)=viewModelScope.launch{

     if(!_listOfOptions.value.isNullOrEmpty()){
         val gluten = _listOfOptions.value?.get(0)
         val lactose = _listOfOptions.value?.get(1)
         val vegetarian = _listOfOptions.value?.get(2)
         val vegan = _listOfOptions.value?.get(3)

         filterCategoryItem(id)
         filterActions(gluten!!, lactose!!, vegetarian!!, vegan!!,_listFilterLocalRecipes)

        }
    }


fun filterCategoryItem(id:String?) {
    _listFilterLocalRecipes.value = _listLocalRecipes.value.filter { recipe ->
        recipe.categories.any { category ->
            category.equals(id)
        }
    }
}

    fun filterActions(gluten:FilterOptions, lactose:FilterOptions, vegetarian:FilterOptions, vegan:FilterOptions,
                    listRecipes:MutableStateFlow<List<LocalRecipe>?>){

        if (gluten.check && lactose.check && vegetarian.check && vegan.check) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isGlutenFree && it.isLactoseFree && it.isVegetarian && it.isVegan
            }
        }
        if (gluten.check && lactose.check && vegetarian.check  ) {
                  listRecipes.value = listRecipes.value!!.filter {
                      it.isGlutenFree && it.isLactoseFree && it.isVegetarian
                  }
              }
        if (gluten.check && lactose.check) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isGlutenFree && it.isLactoseFree
            }
        }

         if (gluten.check) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isGlutenFree
            }
        }


        if ( lactose.check && vegetarian.check && vegan.check) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isLactoseFree && it.isVegetarian && it.isVegan
            }
        }
        if ( lactose.check && vegetarian.check  ) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isLactoseFree && it.isVegetarian
            }
        }
        if (lactose.check  && vegan.check    ) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isVegan && it.isLactoseFree
            }
        }
        if (vegetarian.check  && vegan.check    ) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isVegan && it.isVegetarian
            }
        }

        if (lactose.check) {
            listRecipes.value = listRecipes.value!!.filter {
                it.isLactoseFree
            }
        }

        if (vegan.check) {
                listRecipes.value = listRecipes.value!!.filter {
                    it.isVegan
                }
            }

        if (vegetarian.check) {
                    listRecipes.value = listRecipes.value!!.filter {
                        it.isVegetarian
                    }
        }
    }


    fun filterMeal(id:String){
        _meal.value = _listLocalRecipes.value.filter { recipe->
            recipe.id == id
        }
    }

     fun insertRecipe(recipe: Recipe)= viewModelScope.launch{
         useCases.addRecipe(recipe)
    }

    fun getNotes()= viewModelScope.launch() {
        useCases.getRecipes().distinctUntilChanged()
            .collect{
                _listOfFavorites.value = it
            }
    }
    fun filterFavorites()=viewModelScope.launch {
        delay(100)
        if(!_listOfFavorites.value.isNullOrEmpty()){
            _listFilterLocalRecipes.value = _listLocalRecipes.value.filter { LocalRecipe->
                _listOfFavorites.value?.any { favorite->
                    favorite.id == LocalRecipe.id
                }!!
        }
        }else {
            Log.d("NADENA", "Na de Na")
        }
    }

    fun deleteNote (id:String) = viewModelScope.launch {
            useCases.deleteRecipes(Recipe(id = id))
    }





}