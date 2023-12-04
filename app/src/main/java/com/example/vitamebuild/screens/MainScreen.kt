package com.example.navigationcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.classes.Food

@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "My new meal!", fontSize =30.sp)
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
            }
        ) {
            Text(text = "Log meal")
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
                    route = "MEAL_HISTORY"
                )
            }
        ) {
            Text(text = "Meal history")
        }
    }
}