package com.example.vitamebuild.screens.historyScreens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.generalFunctions.saveToJsonFoodData
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.screens.foodInputScreens.FoodInputFoodName
import com.example.vitamebuild.screens.foodInputScreens.FoodInputFullness
import com.example.vitamebuild.screens.foodInputScreens.FoodInputPlaceEaten
import com.example.vitamebuild.screens.foodInputScreens.FoodInputRating
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTastiness
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTimeEaten
import com.example.vitamebuild.screens.waterInputScreens.InputScreenText
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn

@Composable
fun MealEditScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.edit,
        previousScreenRoute = "MEAL_HISTORY"
    ) {
        MyStyleColumn(textContent = R.string.edit_parameters) {
            var context = LocalContext.current
            FoodInputFoodName(navController)
            InputScreenText(textContent = R.string.at_around)
            //Input field for the time of the meal, by default it's now
            FoodInputTimeEaten(navController)
            //Input field for the date of the meal, by default it's today
            //FoodInputDateEaten() //for now unused
            //Input field for the place of the meal
            FoodInputPlaceEaten()
            //Input field for the amount of food in grams
            //FoodInputWeightInGrams() //for now unused, will decide later if we will use this
            //Input slider for how full the person was feeling with this meal
            FoodInputFullness()
            //Input slider for the tastiness of the food
            FoodInputTastiness()
            //Input slider for the rating of the food
            FoodInputRating()

            SubmitEditButton(navController, "MEAL_HISTORY", ObjectHolder.globalIndex, context)

            Spacer(modifier = Modifier.height(54.dp))

        }

    }
}

@Composable
fun SubmitEditButton(
    navController: NavHostController,
    route: String,
    index: Int,
    context: android.content.Context
    ) {
    Button(onClick = {
        ObjectHolder.globalMealHistoryList[ObjectHolder.globalIndex] = ObjectHolder.newMeal
        navController.navigate("MEAL_HISTORY")
        saveToJsonFoodData(context = context)
    }) {
        Text(text = stringResource(id = R.string.submit_button))
        
    }

}
