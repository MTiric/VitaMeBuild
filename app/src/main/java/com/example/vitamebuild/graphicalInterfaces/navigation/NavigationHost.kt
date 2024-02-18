package com.example.vitamebuild.graphicalInterfaces.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationcomponent.IsThisYourMeal
import com.example.navigationcomponent.MainScreen
import com.example.navigationcomponent.ScreenOne
import com.example.navigationcomponent.ScreenTwo
import com.example.vitamebuild.ObjectHolder
import com.example.vitamebuild.R
import com.example.vitamebuild.graphicalInterfaces.MyScaffold
import com.example.vitamebuild.graphicalInterfaces.navigation.navigationButtons.MyButton
import com.example.vitamebuild.screens.settingsScreens.AppSettingsScreen
import com.example.vitamebuild.screens.BowelMovementsInputScreen
import com.example.vitamebuild.screens.HealthInputScreen
import com.example.vitamebuild.screens.historyScreens.HistoryScreen
import com.example.vitamebuild.screens.personalDataScreens.PersonalDataScreen
import com.example.vitamebuild.screens.PhysicalActivityInputScreen
import com.example.vitamebuild.screens.PrayerMeditationScreen
import com.example.vitamebuild.screens.personalDataScreens.RegisterScreen
import com.example.vitamebuild.screens.personalDataScreens.SignInScreen
import com.example.vitamebuild.screens.SleepInputScreen
import com.example.vitamebuild.screens.foodInputScreens.FoodInputFetchedMeal
import com.example.vitamebuild.screens.foodInputScreens.FoodInputScreen
import com.example.vitamebuild.screens.foodInputScreens.FoodInputTimeScreen
import com.example.vitamebuild.screens.historyScreens.MealEditScreen
import com.example.vitamebuild.screens.settingsScreens.SettingsScreen
import com.example.vitamebuild.screens.waterInputScreens.MyStyleColumn
import com.example.vitamebuild.screens.waterInputScreens.WaterInputScreen

@Composable
fun CustomNavHost(
    navController: NavHostController
) {
    var startDestination: String = "MAIN_SCREEN"
    if (ObjectHolder.isFoodInput){
        startDestination = "FOOD_INPUT_SCREEN"
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
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
            HistoryScreen(navController)
        }
        composable(route = "MEAL_EDIT_SCREEN") {
            MealEditScreen(navController)
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
        composable(route = "REGISTER_SCREEN") {
            RegisterScreen(navController)
        }
        composable(route = "SIGN_IN_SCREEN") {
            SignInScreen(navController)
        }
        composable(route = "CONFIRMATION_SCREEN") {
            ConfirmationScreen(navController)
        }
    }
}

@Composable
fun ConfirmationScreen(navController: NavHostController) {
    MyScaffold(
        navController,
        currentScreenName = R.string.confirmationScreen,
        previousScreenRoute = "PERSONAL_DATA_SCREEN"
    ) {
        MyStyleColumn(textContent =  R.string.confirmationMessage) {
            MyButton(textID = R.string.signIn) {
                navController.navigate(route = "SIGN_IN_SCREEN")

            }
            MyButton(textID = R.string.home) {
                navController.navigate(route = "MAIN_SCREEN")

            }
            
        }

    }

}

