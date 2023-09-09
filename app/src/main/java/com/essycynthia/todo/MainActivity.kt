package com.essycynthia.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.essycynthia.todo.ui.add_edit_todo.AddEditScreen
import com.essycynthia.todo.ui.theme.ToDoTheme
import com.essycynthia.todo.ui.todolist.TodoListScreen
import com.essycynthia.todo.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.TODO_LIST){
                        composable(
                            route = Routes.TODO_LIST
                        ){
                            TodoListScreen(onNavigate = { navController.navigate(it.route) })
                        }
                        composable(
                            route = Routes.ADD_EDIT_TODO + "?todoId = {todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                           AddEditScreen(onPopBackStack = { navController.popBackStack()})
                        }
                    }
                }
            }
        }
    }
}

