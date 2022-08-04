package com.example.mutualmobile.presentation.navigation

import com.example.mutualmobile.Constants

sealed class NaviRoutes(val route: String) {
    object News: NaviRoutes(Constants.NEWS_ROUTE)
    object Sources: NaviRoutes(Constants.SOURCES_ROUTE)
    object Search: NaviRoutes(Constants.SEARCH_ROUTE)
}
