package com.example.vitamebuild.screens.foodInputScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.vitamebuild.graphicalInterfaces.MyTimePickerMeal
import com.example.vitamebuild.graphicalInterfaces.TimePickerTimeHolder

@Composable
fun FoodInputTimeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Select time of the meal")
        MyTimePickerMeal()
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
                MealHolder.meal.foodTimeEaten = TimePickerTimeHolder.timePickerTime
            }
        ) {
            Text(text = "Confirm")
        }
    }
}