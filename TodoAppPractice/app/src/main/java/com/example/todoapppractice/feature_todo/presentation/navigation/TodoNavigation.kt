package com.example.todoapppractice.feature_todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapppractice.feature_todo.presentation.screens.add_edit_todo.AddEditTodoScreen
import com.example.todoapppractice.feature_todo.presentation.screens.todo_list.TodoListScreen

@Composable
fun TodoNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = RouteScreens.TODO_LIST.name ){
        composable(RouteScreens.TODO_LIST.name){
            TodoListScreen(onNavigate = {
                navController.navigate(it.route)
            })
        }
        val routeName = "${RouteScreens.ADD_EDIT_TODO.name}?todoId={todoId}"
        composable(route = routeName, arguments = listOf(
            navArgument(name = "todoId"){
                type = NavType.IntType
                defaultValue = -1
            }
        )){
            AddEditTodoScreen(onPopBackStack = {navController.popBackStack()} )

        }
    }
}
