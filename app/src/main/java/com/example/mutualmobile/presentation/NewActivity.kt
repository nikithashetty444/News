package com.example.mutualmobile.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mutualmobile.R
import com.example.mutualmobile.presentation.model.BottomNavItem
import com.example.mutualmobile.presentation.navigation.NaviRoutes
import com.example.mutualmobile.presentation.navigation.Navigation
import com.example.mutualmobile.presentation.viewmodel.NewsViewModel
import com.example.mutualmobile.widget.BottomNavigationBar
import org.koin.androidx.compose.BuildConfig
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

class NewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NewsHostScreen(navController = navController)

            }
        }
    }
}

@Composable
fun NewsHostScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    ) {
    var showMenu by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "News Feed"
                    )
                },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(Icons.Default.MoreVert, "")
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            onClick = { showMenu = false }
                        ) {
                            Text(text = "List")
                        }
                        DropdownMenuItem(
                            onClick = { showMenu = false }
                        ) {
                            Text(text = "Grid")
                        }
                        DropdownMenuItem(
                            onClick = { showMenu = false }
                        ) {
                            Text(text = "Staggerd")
                        }
                    }
                }

            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(name = "News", route = NaviRoutes.News.route),
                    BottomNavItem(name = "Sources", route = NaviRoutes.Sources.route),
                    BottomNavItem(name = "Search", route = NaviRoutes.Search.route),
                ),
                navController = navController,
                modifier = modifier,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController = navController)
        }
    }

}