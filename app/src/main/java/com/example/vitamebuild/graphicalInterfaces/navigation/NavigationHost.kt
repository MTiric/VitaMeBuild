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
import com.example.vitamebuild.screens.settingsScreens.AppSettingsScreen
import com.example.vitamebuild.screens.BowelMovementsInputScreen
import com.example.vitamebuild.screens.HealthInputScreen
import com.example.vitamebuild.screens.HistoryScreen
import com.example.vitamebuild.screens.PersonalDataScreen
import com.example.vitamebuild.screens.PhysicalActivityInputScreen
import com.example.vitamebuild.screens.PrayerMeditationScreen
import com.example.vitamebuild.screens.SleepInputScreen
import com.example.vitamebuild.screens.foodInputScreens.FoodInputFetchedMeal
import com.example.vitamebuild.screens.foodInputScreens.FoodInputScreen
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTimeScreen
import com.example.vitamebuild.screens.settingsScreens.SettingsScreen
import com.example.vitamebuild.screens.waterInputScreens.WaterInputScreen

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
        composable(route = "FOOD_INTERNET_DATA_SCREEN") {
            FoodInputFetchedMeal(navController)
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
        composable(route = "WATER_INPUT_SCREEN") {
            WaterInputScreen(navController)
        }
        composable(route = "SLEEP_INPUT_SCREEN") {
            SleepInputScreen(navController)
        }
        composable(route = "BOWEL_MOVEMENTS_INPUT_SCREEN") {
            BowelMovementsInputScreen(navController)
        }
        composable(route = "PHYSICAL_ACTIVITY_INPUT_SCREEN") {
            PhysicalActivityInputScreen(navController)
        }
        composable(route = "HEALTH_INPUT_SCREEN") {
            HealthInputScreen(navController)
        }
        composable(route = "HISTORY_SCREEN") {
            HistoryScreen(navController)
        }
        composable(route = "PERSONAL_DATA_SCREEN") {
            PersonalDataScreen(navController)
        }
        composable(route = "PRAYER_MEDITATION_SCREEN") {
            PrayerMeditationScreen(navController)
        }
        composable(route = "APP_SETTINGS_SCREEN") {
            AppSettingsScreen(navController)
        }
    }
}

