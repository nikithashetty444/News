package com.example.mutualmobile.widget

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorWidget(
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {

        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Oops!", color = Color.Black) },
            text = { Text("Something went wrong", color = Color.Black) },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                       onPositiveClick.invoke()
                    }
                ) {
                    Text("Try again", color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                       onNegativeClick.invoke()
                    }
                ) {
                    Text("Dismiss", color = Color.Black)
                }
            }
        )
    }
}