package com.example.checkyourgitrepo

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class Screen(val route: String){
    object Repos: Screen("repos")
    object Languages: Screen("languages")
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Screen.Repos.route
    ){
        composable(Screen.Repos.route){
            ReposScreen(navController)
        }
        composable(
            Screen.Languages.route + "/{repo}",
            arguments = listOf(
                navArgument("repo"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ){
            val repo = it.arguments?.getString("repo") ?: ""
            LanguagesScreen(navController, repo = repo)
        }
    }
}