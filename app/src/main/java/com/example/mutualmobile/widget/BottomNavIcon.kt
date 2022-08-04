package com.example.mutualmobile.widget

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun BottomNavIcon(
    modifier: Modifier,
    id: Int
) {
    Icon(
        painter = painterResource(id = id),
        contentDescription = "Icon",
        modifier = modifier
    )
}