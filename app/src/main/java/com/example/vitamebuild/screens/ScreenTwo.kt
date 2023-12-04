package com.example.navigationcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ScreenTwo(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Screen Two")
        Button(
            onClick = {
                navController.navigate("SCREEN_ONE")
            }
        ) {
            Text(text = "Go to screen One")
        }
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
            }
        ) {
            Text(text = "New meal")
        }
        Button(
            onClick = {
                navController.navigate(
                    route = "MAIN_SCREEN"
                )
            }
        ) {
            Text(text = "Main screen")
        }
    }
}