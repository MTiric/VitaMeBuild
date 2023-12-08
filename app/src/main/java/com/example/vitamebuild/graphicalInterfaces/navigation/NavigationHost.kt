package com.example.vitamebuild.graphicalInterfaces.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationcomponent.IsThisYourMeal
import com.example.navigationcomponent.MainScreen
import com.example.navigationcomponent.MealHistory
import com.example.navigationcomponent.ScreenOne
import com.example.navigationcomponent.ScreenTwo
import com.example.vitamebuild.screens.foodInputScreens.FoodInputScreen
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTimeEaten
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTimeScreen
import com.example.vitamebuild.screens.settings.SettingsScreen

@Composable
fun CustomNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "MAIN_SCREEN"
    ) {
        composable(route = "SCREEN_ONE") {
            ScreenOne(navController)
        }
        composable(route = "SCREEN_TWO") {
            ScreenTwo(navController)
        }
        composable(route = "FOOD_INPUT_SCREEN") {
            FoodInputScreen(navController)
        }
        composable(route = "MAIN_SCREEN") {
            MainScreen(navController)
        }
        composable(route = "MEAL_HISTORY") {
            MealHistory(navController)
        }
        composable(route = "IS_THIS_YOUR_MEAL") {
            IsThisYourMeal(navController)
        }
        composable(route = "SETTINGS_SCREEN"){
            SettingsScreen(navController)
        }
        composable(route = "FOOD_INPUT_TIME_SCREEN"){
            FoodInputTimeScreen(navController)
        }
    }
}