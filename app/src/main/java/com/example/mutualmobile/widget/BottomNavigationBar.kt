package com.example.mutualmobile.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mutualmobile.Constants
import com.example.mutualmobile.R
import com.example.mutualmobile.presentation.model.BottomNavItem

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backstackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = dimensionResource(id = R.dimen.dimen_5dp)
    ) {
        items.forEach { item ->
            val selected = item.route == backstackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(item)
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    when (item.route) {
                        Constants.NEWS -> BottomNavIcon(
                            modifier = modifier,
                            id = R.drawable.ic_news
                        )
                        Constants.SOURCES -> BottomNavIcon(
                            modifier = modifier,
                            id = R.drawable.ic_source
                        )
                        Constants.ARCHIVES -> BottomNavIcon(
                            modifier = modifier,
                            id = R.drawable.ic_archive
                        )
                        Constants.SEARCH -> BottomNavIcon(
                            modifier = modifier,
                            id = R.drawable.ic_search
                        )
                    }
                },
                label = {
                    Text(text = item.name)
                }
            )
        }
    }
}