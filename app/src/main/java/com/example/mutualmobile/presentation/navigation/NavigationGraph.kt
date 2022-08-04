package com.example.mutualmobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mutualmobile.widget.NewsList
import com.example.mutualmobile.widget.SearchBar
import com.example.mutualmobile.widget.SearchHostScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NaviRoutes.News.route) {
        composable(NaviRoutes.News.route) {
            NewsList(newsType = NaviRoutes.News.route)
        }
        composable(NaviRoutes.Sources.route) {
            NewsList(newsType = NaviRoutes.Sources.route)
        }
        composable(NaviRoutes.Search.route) {
            SearchHostScreen()
        }
    }

}