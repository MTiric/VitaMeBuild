package com.example.navigationcomponent

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder

import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.generalFunctions.loadJsonFileFoodData
import com.example.vitamebuild.generalFunctions.saveToJsonFoodData

@Composable
fun IsThisYourMeal(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var context = LocalContext.current
        Text(text = stringResource(
            id = R.string.is_this_your_meal,
            ObjectHolder.newMeal.foodName,
            ObjectHolder.newMeal.foodTimeEaten), fontSize = 30.sp)
        Button(
            onClick = {
                navController.navigate(
                    route = "FOOD_INPUT_SCREEN"
                )
            }
        ) {
            Text(text = stringResource(id = R.string.no_its_not_my_meal))
        }

        Button(
            onClick = {
                navController.navigate(
                    route = "MAIN_SCREEN"
                )
                ObjectHolder.globalMealHistoryList.add(0, ObjectHolder.newMeal)
                Log.i("TestStoredValue", "storedValue: ${ObjectHolder.globalMealHistoryList.last().foodContent.description}")
                saveToJsonFoodData(context)

            }
        ) {
            Text(text = stringResource(id = R.string.yes_its_my_meal) )
        }
    }
}