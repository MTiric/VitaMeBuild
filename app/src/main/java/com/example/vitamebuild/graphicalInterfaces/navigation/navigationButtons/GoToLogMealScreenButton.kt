package com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.vitamebuild.DefaultMealHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.generalFunctions.getCurrentDateAsString
import com.example.vitamebuild.generalFunctions.getCurrentTimeAsString

@Composable
fun GoToLogMealScreenButton(navController: NavHostController) {
    Button(
        onClick = {
            DefaultMealHolder.defaultMeal = Food(
                foodName = "",
                timeEaten = getCurrentTimeAsString(),
                dateEaten = getCurrentDateAsString()
            )
            navController.navigate(
                route = "FOOD_INPUT_SCREEN"
            )
        }
    ) {
        Text(text = stringResource(id = R.string.log_meal))
    }
}