package com.example.vitamebuild.screens.historyScreens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigationcomponent.MealHistory
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.classes.Food
import com.example.vitamebuild.generalFunctions.saveToJsonFoodData
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HistoryScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.meal_history,
        previousScreenRoute = "MAIN_SCREEN") {
        MyStyleColumn(textContent = R.string.history_of_eaten_meals) {
            ObjectHolder.globalMealHistoryList.forEachIndexed { index, food ->
                MealHistorySegment(food = food, index = index, navController) {}
                
            }
            Spacer(modifier = Modifier.padding(50.dp))

        }
        
    }

}

@Composable
fun MealHistorySegment(
    food: Food,
    index: Int,
    navController: NavHostController,
    onEditClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        var context = LocalContext.current
        val scope = rememberCoroutineScope()
        Text(
            text = food.foodDateEaten + " " + food.foodTimeEaten,
            fontWeight = FontWeight(18),
            fontSize = 18.sp
        )
        Text(text = "Food: ${food.foodName}")
        Text(text = "Satisfaction: ${food.foodTastiness}")
        Text(text = "Rating: ${food.rating}")
        Text(text = "Fullness: ${food.foodAmountFullness}")

        Button(onClick = {
            ObjectHolder.newMeal = ObjectHolder.globalMealHistoryList[index]
            ObjectHolder.globalIndex = index
            navController.navigate(route = "MEAL_EDIT_SCREEN")
        }) {
            Text(text = stringResource(id = R.string.edit))

        }

        Button(onClick = {
            scope.launch {
                ObjectHolder.globalMealHistoryList.removeAt(index)
                navController.navigate( route = "MEAL_HISTORY")
                saveToJsonFoodData(context)
                Log.i("Test_Deletion", "Meal deleted successfuly")
            }

        }) {
            Text(text = stringResource(id = R.string.delete))


        }

    }
}


