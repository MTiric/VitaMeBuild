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
import com.example.vitamebuild.screens.MealHolder

@Composable
fun IsThisYourMeal(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Is this your meal?", fontSize = 30.sp)
        Text(text = MealHolder.meal.foodName)
        Text(text = MealHolder.meal.foodTimeEaten)
        Text(text = MealHolder.meal.foodDateEaten)
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
            }
        ) {
            Text(text = "No, that's not my meal")
        }

        Button(
            onClick = {
                navController.navigate(
                    route = "MAIN_SCREEN"
                )
            }
        ) {
            Text(text = "Yes, that is my meal")
        }
    }
}